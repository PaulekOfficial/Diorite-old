/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016. Diorite (by Bartłomiej Mazur (aka GotoFinal))
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.diorite.impl.pipelines.event.player;

import java.util.Collection;
import java.util.Objects;

import org.diorite.impl.CoreMain;
import org.diorite.impl.connection.packets.play.clientbound.PacketPlayClientboundTransaction;
import org.diorite.impl.entity.IPlayer;
import org.diorite.impl.inventory.InventoryViewImpl;
import org.diorite.impl.inventory.PlayerInventoryImpl;
import org.diorite.impl.inventory.item.ItemStackImpl;
import org.diorite.GameMode;
import org.diorite.event.pipelines.event.player.InventoryClickPipeline;
import org.diorite.event.player.PlayerInventoryClickEvent;
import org.diorite.inventory.ClickType;
import org.diorite.inventory.item.BaseItemStack;
import org.diorite.inventory.item.ItemStack;
import org.diorite.inventory.slot.Slot;
import org.diorite.inventory.slot.SlotType;
import org.diorite.material.items.ArmorMat;
import org.diorite.material.items.BootsMat;
import org.diorite.material.items.ChestplateMat;
import org.diorite.material.items.HelmetMat;
import org.diorite.material.items.LeggingsMat;
import org.diorite.utils.pipeline.SimpleEventPipeline;

public class InventoryClickPipelineImpl extends SimpleEventPipeline<PlayerInventoryClickEvent> implements InventoryClickPipeline
{
    @Override
    public void reset_()
    {
        this.addLast("Diorite|Handle", (evt, pipeline) -> {
            final IPlayer player = (IPlayer) evt.getPlayer();

            if (evt.isCancelled())
            {
                player.getNetworkManager().sendPacket(new PacketPlayClientboundTransaction(evt.getWindowId(), evt.getActionNumber(), false));
                return;
            }
            final boolean accepted = this.handleClick(evt, player, evt.getClickType());
            player.getNetworkManager().sendPacket(new PacketPlayClientboundTransaction(evt.getWindowId(), evt.getActionNumber(), accepted));

            if (! accepted)
            {
                System.out.println("Rejected inventory click action from player " + evt.getPlayer().getName());
            }
        });
    }

    protected boolean handleClick(final PlayerInventoryClickEvent e, final IPlayer player, final ClickType ct)
    {
        CoreMain.debug("InventoryClick = " + e);
        ItemStackImpl.validate(e.getCursorItem());

        final int slot = e.getClickedSlot();
        final InventoryViewImpl inv = player.getInventoryView();
        final Slot slotProp = inv.getSlot(slot);

        final ItemStackImpl cursor  = ItemStackImpl.wrap(e.getCursorItem());
        final ItemStackImpl clicked = ItemStackImpl.wrap(e.getClickedItem());

        try
        {
            if (Objects.equals(ct, ClickType.MOUSE_LEFT))
            {
                return this.handleLeftClick(slot, slotProp, inv, cursor, clicked);
            }
            else if (Objects.equals(ct, ClickType.MOUSE_RIGHT))
            {
                return this.handleRightClick(slot, slotProp, inv, cursor, clicked);
            }
            else if (Objects.equals(ct, ClickType.SHIFT_MOUSE_LEFT) || Objects.equals(ct, ClickType.SHIFT_MOUSE_RIGHT))
            {
                if (inv.hasUpperInventory())
                {
                    return false; // TODO clicking with shift in other inventories than Player must have own logic
                }
                return this.handleShiftClickInPlayerEq(e.getActionNumber(), slot, slotProp, inv, clicked);
            }
            else if (Objects.equals(ct, ClickType.DROP_KEY))
            {
                if (clicked == null)
                {
                    return true;
                }
                if (clicked.getAmount() == 1)
                {
                    if (! inv.replace(slot, clicked, null))
                    {
                        return false;
                    }

                    player.dropItem(new BaseItemStack(clicked));
                }
                else
                {
                    final ItemStack toDrop = clicked.split(1);

                    player.dropItem(new BaseItemStack(toDrop));
                }
            }
            else if (Objects.equals(ct, ClickType.CTRL_DROP_KEY))
            {
                if (! inv.replace(slot, clicked, null))
                {
                    return false;
                }
                player.dropItem(new BaseItemStack(clicked));
            }
            else if (ct.getMode() == 2) // 2 -> hot bar action
            {
                if ((ct.getButton() < 0) || (ct.getButton() > 8) || inv.hasUpperInventory())
                {
                    return false;
                }
                final PlayerInventoryImpl playerInv = player.getInventory();
                final ItemStack inHeldSlot = playerInv.getHotbarInventory().getItem(ct.getButton());
                return inv.replace(slot, clicked, inHeldSlot) && playerInv.getHotbarInventory().replace(ct.getButton(), inHeldSlot, clicked);
            }
            else if (Objects.equals(ct, ClickType.MOUSE_LEFT_OUTSIDE))
            {
                if ((cursor == null) || (cursor.getAmount() <= 0))
                {
                    inv.setCursorItem(null);
                    return true;
                }
                if (! inv.replaceCursorItem(cursor, null))
                {
                    return false;
                }
                player.dropItem(new BaseItemStack(cursor));
                return true;
            }
            else if (Objects.equals(ct, ClickType.MOUSE_RIGHT_OUTSIDE))
            {
                if ((cursor == null) || (cursor.getAmount() <= 0))
                {
                    inv.setCursorItem(null);
                    return true;
                }
                final ItemStack it = new BaseItemStack(cursor.getMaterial(), 1);
                it.setItemMeta(cursor.getItemMeta().clone());
                if (cursor.getAmount() == 1)
                {
                    if (! inv.replaceCursorItem(cursor, null))
                    {
                        return false;
                    }
                    player.dropItem(it);
                    return true;
                }
                cursor.setAmount(cursor.getAmount() - 1);
                player.dropItem(it);
                return true;
            }
            else if (Objects.equals(ct, ClickType.MOUSE_MIDDLE))
            {
                if (! Objects.equals(player.getGameMode(), GameMode.CREATIVE))
                {
                    return true;
                }

                if ((cursor != null) && (clicked != null))
                {
                    return true;
                }

                final ItemStack newCursor = new BaseItemStack(clicked);
                newCursor.setAmount(newCursor.getMaterial().getMaxStack());
                return inv.replaceCursorItem(null, newCursor);
            }
            else if (Objects.equals(ct, ClickType.MOUSE_LEFT_DRAG_START) || Objects.equals(ct, ClickType.MOUSE_RIGHT_DRAG_START))
            {
                return inv.getDragController().start(ct.getButton() == ClickType.MOUSE_RIGHT_DRAG_START.getButton());
            }
            else if (Objects.equals(ct, ClickType.MOUSE_LEFT_DRAG_ADD) || Objects.equals(ct, ClickType.MOUSE_RIGHT_DRAG_ADD))
            {
                return inv.getDragController().addSlot(ct.getButton() == ClickType.MOUSE_RIGHT_DRAG_ADD.getButton(), slot);
            }
            else if (Objects.equals(ct, ClickType.MOUSE_LEFT_DRAG_END) || Objects.equals(ct, ClickType.MOUSE_RIGHT_DRAG_END))
            {
                final boolean isRightClick = ct.getButton() == ClickType.MOUSE_RIGHT_DRAG_END.getButton();
                final Collection<Integer> result = inv.getDragController().end(isRightClick);
                if ((cursor == null) || (result == null))
                {
                    return false;
                }

                ItemStack newCursor = new BaseItemStack(cursor);

                final int perSlot = isRightClick ? 1 : (cursor.getAmount() / result.size());
                for (final int dragSlot : result)
                {
                    final ItemStackImpl oldItem = ItemStackImpl.wrap(inv.getItem(dragSlot));
                    if ((oldItem == null) || cursor.isSimilar(oldItem))
                    {
                        final ItemStack itemStackToCombine = new BaseItemStack(cursor);
                        itemStackToCombine.setAmount(perSlot);

                        final int combined;

                        if (oldItem != null)
                        {
                            final ItemStackImpl uncomb = oldItem.combine(itemStackToCombine);
                            combined = perSlot - ((uncomb == null) ? 0 : uncomb.getAmount());
                        }
                        else
                        {
                            combined = perSlot;
                            final ItemStack it = new BaseItemStack(newCursor.getMaterial(), combined);
                            it.setItemMeta(newCursor.getItemMeta().clone());
                            inv.setItem(dragSlot, it);
                        }

                        newCursor.setAmount(newCursor.getAmount() - combined);
                        if (newCursor.getAmount() == 0)
                        {
                            newCursor = null;
                            break;
                        }
                    }
                }

                return inv.replaceCursorItem(cursor, newCursor);
            }
            else if (Objects.equals(ct, ClickType.DOUBLE_CLICK))
            {
                if (cursor == null)
                {
                    return true;
                }

                final int slots = 44;

                int firstFullSlot = - 1; // minecraft skip full stacks and use them only if there is no smaller ones
                for (int i = 0; (i < slots) && (cursor.getAmount() < cursor.getMaterial().getMaxStack()); i++)
                {
                    final ItemStack item = inv.getItem(i);
                    if ((item == null) || ! cursor.isSimilar(item) /* || slot type == RESULT */)
                    {
                        continue;
                    }
                    if (item.getAmount() >= item.getMaterial().getMaxStack())
                    {
                        if (firstFullSlot == - 1)
                        {
                            firstFullSlot = i;
                        }
                        continue;
                    }

                    if (! this.doubleClick(inv, i, cursor))
                    {
                        return false;
                    }
                }
                if ((firstFullSlot != - 1) && (cursor.getAmount() < cursor.getMaterial().getMaxStack()))
                {
                    if (! this.doubleClick(inv, firstFullSlot, cursor))
                    {
                        return false;
                    }
                }
            }
            else if (Objects.equals(ct, ClickType.SWAP_OFF_HAND))
            {
                final ItemStackImpl second = ItemStackImpl.wrap(inv.getItem(PlayerInventoryImpl.SECOND_HAND_SLOT));
                boolean secondSlot = inv.replace(PlayerInventoryImpl.SECOND_HAND_SLOT, second, e.getClickedItem());
                boolean hand = inv.replace(e.getClickedSlot(), e.getClickedItem(), second);

                if (second != null)
                {
                    second.setDirty();
                }
                return hand && secondSlot;
            }
            // TODO remember about throwing item on cursor to ground when closing eq
            else
            {
                return false; // Action not supported
            }
            return true;
        } finally
        {
            if (clicked != null)
            {
                clicked.setDirty();
            }
            if (cursor != null)
            {
                cursor.setDirty();
            }
        }
    }

    protected boolean handleLeftClick(final int slot, final Slot slotProp, final InventoryViewImpl inv, final ItemStackImpl cursor, final ItemStackImpl clicked)
    {
        if (slot == - 1) // click in non-slot place, like inventory border.
        {
            return true;
        }
        if (slotProp.getSlotType().equals(SlotType.RESULT))
        {
            return this.confirmCraftingRecipe(inv, false);
        }
        if (cursor == null)
        {
            if ((clicked != null) && (! inv.replace(slot, clicked, null) || ! inv.replaceCursorItem(null, clicked)))
            {
                return false; // item changed before we made own change
            }
        }
        else
        {
            if (cursor.isSimilar(clicked))
            {
                final ItemStack newCursor = clicked.combine(cursor);

                if (! inv.replaceCursorItem(cursor, newCursor))
                {
                    return false;
                }
            }
            else
            {
                final ItemStack item = slotProp.canHoldItem(cursor);
                if (item == null)
                {
                    return true;
                }
                else if (! inv.replace(slot, clicked, cursor) || ! inv.replaceCursorItem(cursor, clicked))
                {
                    return false; // item changed before we made own change
                }
            }
        }
        return true;
    }

    protected boolean handleRightClick(final int slot, final Slot slotProp, final InventoryViewImpl inv, final ItemStackImpl cursor, final ItemStackImpl clicked)
    {
        if (slot == - 1) // click in non-slot place, like inventory border.
        {
            return true;
        }
        if (slotProp.getSlotType().equals(SlotType.RESULT))
        {
            return this.confirmCraftingRecipe(inv, false);
        }
        if (cursor == null)
        {
            if (clicked == null)
            {
                return true;
            }
            final ItemStack newCursor = clicked.split(this.getAmountToStayInHand(clicked.getAmount()));
            if (clicked.getAmount() == 0)
            {
                if (! inv.replace(slot, clicked, null))
                {
                    return false;
                }
            }
            if (! inv.replaceCursorItem(/*cursor*/ null, newCursor))
            {
                return false;
            }
        }
        else // cursor != null
        {
            if (clicked == null)
            {
                final ItemStack item = slotProp.canHoldItem(cursor);
                if (item == null)
                {
                    return true;
                }
                final ItemStack splitted = cursor.split(1);
                if (cursor.getAmount() == 0)
                {
                    if (! inv.replaceCursorItem(cursor, null))
                    {
                        return false;
                    }
                }

                if (! inv.replace(slot,/*clicked*/ null, splitted))
                {
                    return false;
                }
            }
            else // clicked != null
            {
                if (clicked.isSimilar(cursor))
                {
                    if (clicked.getAmount() >= clicked.getMaterial().getMaxStack())
                    {
                        return true; // no place to add more items.
                    }

                    final ItemStack temp = new BaseItemStack(clicked);
                    temp.setAmount(temp.getAmount() + 1);
                    final ItemStack item = slotProp.canHoldItem(temp);
                    if (temp != item) // this slot can't hold more items as canHold returned other stack than given when used bigger stack.
                    {
                        return true;
                    }
                    final ItemStack rest = clicked.addFrom(cursor, 1);
                    if (cursor.getAmount() == 0)
                    {
                        if (! inv.replaceCursorItem(cursor, null))
                        {
                            return false;
                        }
                    }
                    if (rest != null)
                    {
                        return false; // it should never happen as we are only adding 1 item.
                    }
                }
                else
                {
                    final ItemStack item = slotProp.canHoldItem(cursor);
                    if (item == null)
                    {
                        return true;
                    }
                    else if (! inv.replace(slot, clicked, cursor) || ! inv.replaceCursorItem(cursor, clicked))
                    {
                        return false;
                    }
                }
            }
        } // end cursor != null
        return true;
    }

    protected boolean handleShiftClickInPlayerEq(final int actionNumber, final int slot, final Slot slotProp, final InventoryViewImpl inv, final ItemStackImpl clicked)
    {
        if (clicked == null)
        {
            return true;
        }
        if (slotProp.getSlotType().equals(SlotType.RESULT))
        {
            this.confirmCraftingRecipe(inv, true);
            return true;
        }

        final ItemStack[] rest;
        final PlayerInventoryImpl playerInv = (PlayerInventoryImpl) inv.getLowerInventory();

        if ((slotProp.getSlotType().equals(SlotType.CONTAINER) || slotProp.getSlotType().equals(SlotType.HOTBAR) || slotProp.getSlotType().equals(SlotType.SECOND_HAND)) && (clicked.getMaterial() instanceof ArmorMat))
        {
            //TODO Item update animation is called even though nothing happens (ex helmet equpied but trying to equip another)
            ArmorMat mat = (ArmorMat) clicked.getMaterial();
            if (mat instanceof HelmetMat && playerInv.getHelmet() == null) //TODO better way ?
            {
                return playerInv.replaceHelmet(null, clicked) && inv.replace(slot, clicked, null);
            }
            else if (mat instanceof ChestplateMat && playerInv.getChestplate() == null)
            {
                return playerInv.replaceChestplate(null, clicked) && inv.replace(slot, clicked, null);
            }
            else if (mat instanceof LeggingsMat && playerInv.getLeggings() == null)
            {
                return playerInv.replaceLeggings(null, clicked) && inv.replace(slot, clicked, null);
            }
            else if (mat instanceof BootsMat && playerInv.getBoots() == null)
            {
                return playerInv.replaceBoots(null, clicked) && inv.replace(slot, clicked, null);
            }
            else if (actionNumber == - 2)
            {
                return false;
            }
        }
        if (slotProp.getSlotType().equals(SlotType.CONTAINER))
        {
            // clicked on other slot
            rest = playerInv.getHotbarInventory().add(clicked);
        }
        else
        {
            // clicked on hotbar
            rest = playerInv.getEqInventory().add(clicked);
        }
        if (rest.length == 0)
        {
            if (! inv.replace(slot, clicked, null))
            {
                return false;
            }
        }
        else
        {
            clicked.setAmount(rest[0].getAmount());
        }
        return true;
    }

    protected boolean doubleClick(final InventoryViewImpl inv, final int slot, final ItemStack cursor)
    {
        final ItemStack item = inv.getItem(slot);
        final int newCursor = cursor.getAmount() + Math.min(item.getAmount(), cursor.getMaterial().getMaxStack() - cursor.getAmount());
        final int newItem = item.getAmount() - (newCursor - cursor.getAmount());
        cursor.setAmount(newCursor);
        if (newItem <= 0)
        {
            if (! inv.replace(slot, item, null))
            {
                return false;
            }
        }
        else
        {
            item.setAmount(newItem);
        }
        return true;
    }

    protected int getAmountToStayInHand(final int amount)
    {
        return ((amount % 2) == 0) ? (amount / 2) : ((amount / 2) + 1);
    }

    protected boolean confirmCraftingRecipe(final InventoryViewImpl inventoryView, final boolean all)
    {
        if (!inventoryView.hasUpperInventory())
        {
            // Crafting in player inventory
            ((PlayerInventoryImpl) inventoryView.getLowerInventory()).getCraftingInventory().confirmRecipe(all);
        }
        else
        {
            // We has upper inventory. It means that we're crafting in Crafting Table
            // TODO implement this
        }

        return true;
    }
}
