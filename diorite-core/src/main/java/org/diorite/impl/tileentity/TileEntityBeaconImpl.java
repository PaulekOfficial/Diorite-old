package org.diorite.impl.tileentity;

import static org.diorite.material.Material.DIAMOND_BLOCK;
import static org.diorite.material.Material.EMERALD_BLOCK;
import static org.diorite.material.Material.GOLD_BLOCK;
import static org.diorite.material.Material.IRON_BLOCK;


import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.impl.inventory.block.BeaconInventoryImpl;
import org.diorite.block.Beacon;
import org.diorite.block.Block;
import org.diorite.block.BlockLocation;
import org.diorite.inventory.block.BeaconInventory;
import org.diorite.inventory.item.ItemStack;
import org.diorite.inventory.item.meta.PotionMeta.PotionTypes;
import org.diorite.material.BlockMaterialData;
import org.diorite.tileentity.TileEntityBeacon;
import org.diorite.utils.math.DioriteRandom;

public class TileEntityBeaconImpl extends TileEntityImpl implements TileEntityBeacon
{
    private final Beacon          block;
    private       BeaconInventory inventory;
    private       int             level;
    private       PotionTypes     primary;
    private       PotionTypes     secondary;

    public TileEntityBeaconImpl(final Beacon block)
    {
        super(block.getLocation());

        this.block = block;
        this.level = 0;
        this.primary = null;
        this.secondary = null;
        this.inventory = new BeaconInventoryImpl(block);
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

    protected int calculateLevel()
    {
        final BlockLocation location = this.block.getLocation();

        for (int level = 1; level < 4; level++)
        {
            final BlockLocation levelLoc = location.subtractY(level);
            if (! this.checkPyramid(levelLoc, level))
            {
                return level - 1;
            }
        }

        return 4;
    }

    protected boolean checkPyramid(final BlockLocation location, final int level)
    {
        // TODO double check math
        // 3x3 -- level 1
        // 1 + 1*2 / 2 - 0.5 =
        // 1 + 2   / 2 - 0.5 =
        // 3       / 2 - 0.5 =
        // 1.5         - 0.5 =
        // 1
        // For loop: -1, 0, 1
        final int size = (int) (((1 + (level << 1)) / 2) - 0.5);

        for (int x = - size; x < size; x++)
        {
            for (int z = - size; z < size; z++)
            {
                if (! this.isValidBlock(location.add(x, 0, z).getBlock().getType()))
                {
                    return false;
                }
            }
        }

        return true;
    }

    protected boolean isValidBlock(final BlockMaterialData block)
    {
        return (block == EMERALD_BLOCK) || (block == DIAMOND_BLOCK) || (block == GOLD_BLOCK) || (block == IRON_BLOCK);
    }

    @Override
    public int getLevel()
    {
        return this.level;
    }

    @Override
    public void setLevel(final int level)
    {
        this.level = level;
    }

    @Override
    public PotionTypes getPrimaryEffect()
    {
        return this.primary;
    }

    @Override
    public void setPrimaryEffect(final PotionTypes effect)
    {
        this.primary = effect;
    }

    @Override
    public PotionTypes getSecondaryEffect()
    {
        return this.secondary;
    }

    @Override
    public void setSecondaryEffect(final PotionTypes effect)
    {
        this.secondary = effect;
    }

    @Override
    public BeaconInventory getInventory()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("block", this.block).append("level", this.level).append("primary", this.primary).append("secondary", this.secondary).toString();
    }
}
