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

import org.diorite.entity.Zombie;
import org.diorite.utils.math.geometry.ImmutableEntityBoundingBox;

public interface IZombie extends IMonsterEntity, Zombie
{
    ImmutableEntityBoundingBox BASE_SIZE = new ImmutableEntityBoundingBox(0.6F, 1.8F);

    /**
     * Size of metadata.
     */
    byte META_KEYS                          = 16;
    /**
     * boolean
     */
    byte META_KEY_ZOMBIE_IS_BABY            = 12;
    /**
     * int, zombie type
     * 0 = Default, 1-5 = Villager Profession, 6 = Husk
     */
    byte META_KEY_ZOMBIE_TYPE               = 13;
    /**
     * boolean
     */
    byte META_KEY_ZOMBIE_IS_CONVERTING      = 14;
    /**
     * boolean
     */
    byte META_KEY_ZOMBIE_ARE_HANDS_RISED_UP = 15;
}
