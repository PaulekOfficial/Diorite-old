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

package org.diorite.impl.entity;

import org.diorite.entity.Minecart;
import org.diorite.utils.math.geometry.ImmutableEntityBoundingBox;

public interface IAbstractMinecart extends IEntity, Minecart, EntityObject
{
    ImmutableEntityBoundingBox BASE_SIZE = new ImmutableEntityBoundingBox(0.98F, 0.7F);

    /**
     * Size of metadata.
     */
    byte META_KEYS                            = 12;
    /**
     * int
     */
    byte META_KEY_MINECART_SHAKING_POWER      = 6;
    /**
     * int, enum?
     */
    byte META_KEY_MINECART_SHAKING_DIRECTION  = 7;
    /**
     * float
     */
    byte META_KEY_MINECART_SHAKING_MULTIPLIER = 8;
    /**
     * int, id and meta of material.
     */
    byte META_KEY_MINECART_BLOCK              = 9;
    /**
     * int
     */
    byte META_KEY_MINECART_BLOCK_Y_POSITION   = 10;
    /**
     * boolean
     */
    byte META_KEY_MINECART_SHOW_BLOCK         = 11;
}
