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

package org.diorite.impl.block;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.impl.world.WorldImpl;
import org.diorite.impl.world.chunk.ChunkImpl;
import org.diorite.block.Block;
import org.diorite.block.BlockLocation;
import org.diorite.block.BlockState;
import org.diorite.material.BlockMaterialData;
import org.diorite.world.World;

public class BlockStateImpl implements BlockState
{
    private final int               x;
    private final int               y;
    private final int               z;
    private final WorldImpl         world;
    private final ChunkImpl         chunk;
    private final BlockMaterialData type;

    public BlockStateImpl(final Block block)
    {
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.world = (WorldImpl) block.getWorld();
        this.chunk = (ChunkImpl) block.getChunk();
        this.type = block.getType();
    }

    public static BlockStateImpl getBlockState(final World world, final BlockLocation location)
    {
        return new BlockStateImpl(world.getBlock(location));
    }

    @Override
    public int getX()
    {
        return this.x;
    }

    @Override
    public int getY()
    {
        return this.y;
    }

    @Override
    public int getZ()
    {
        return this.z;
    }

    @Override
    public Block getBlock()
    {
        return this.world.getBlock(this.x, this.y, this.z);
    }

    @Override
    public BlockMaterialData getType()
    {
        return this.type;
    }

    @Override
    public void setType(final BlockMaterialData type)
    {
        //TODO
    }

    @Override
    public World getWorld()
    {
        return this.world;
    }

    @Override
    public BlockLocation getLocation()
    {
        return new BlockLocation(this.x, this.y, this.z);
    }

    @Override
    public boolean update()
    {
        return this.update(false);
    }

    @Override
    public boolean update(final boolean force)
    {
        return this.update(force, true);
    }

    @Override
    public boolean update(final boolean force, final boolean applyPhysics)
    {
        this.requirePlaced();

        Block block = this.getBlock();

        if ((block.getType() != this.type) && ! force)
        {
            return false;
        }

        //TODO

        return true;
    }

    @Override
    public boolean isPlaced()
    {
        return this.world != null;
    }

    protected void requirePlaced()
    {
        if (! this.isPlaced())
        {
            throw new IllegalStateException("Block must be placed to call this method");
        }
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("x", this.x).append("y", this.y).append("z", this.z).append("world", this.world).append("chunk", this.chunk).append("type", this.type).toString();
    }
}
