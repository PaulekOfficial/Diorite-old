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

package org.diorite.impl.tileentity;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.impl.inventory.block.ChestInventoryImpl;
import org.diorite.block.Block;
import org.diorite.block.Chest;
import org.diorite.inventory.Inventory;
import org.diorite.inventory.InventoryType;
import org.diorite.inventory.item.ItemStack;
import org.diorite.nbt.NbtTagCompound;
import org.diorite.tileentity.TileEntityChest;
import org.diorite.utils.math.DioriteRandom;

public class TileEntityChestImpl extends TileEntityImpl implements TileEntityChest
{
    private final Chest block;
    private ItemStack[] items = new ItemStack[InventoryType.CHEST.getSize()];
    private Inventory inventory;

    public TileEntityChestImpl(final Chest block)
    {
        super(block.getLocation());

        this.block = block;
        this.inventory = new ChestInventoryImpl(block); //TODO: check for double chest
    }

    @Override
    public void doTick(final int tps)
    {
        //TODO
    }

    @Override
    public Block getBlock()
    {
        return this.block.getBlock();
    }

    @Override
    public void simulateDrop(final DioriteRandom rand, final Set<ItemStack> drops)
    {
        //TODO
    }

    @Override
    public void loadFromNbt(final NbtTagCompound nbtTileEntity)
    {
        super.loadFromNbt(nbtTileEntity);

        //TODO: load items from NBT
    }

    @Override
    public void saveToNbt(final NbtTagCompound nbtTileEntity)
    {
        super.saveToNbt(nbtTileEntity);
        nbtTileEntity.setList("Items", new ArrayList<>(0));

        //TODO: save items to NBT
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("block", this.block).toString();
    }

    @Override
    public Inventory getInventory()
    {
        return this.inventory;
    }
}
