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

package org.diorite;

import java.util.Collection;

public interface BossBarHolder
{
    /**
     * Adds {@link BossBar} to this holder and for all childs
     *
     * @param bossBar which should be added
     */
    void addBossBar(BossBar bossBar);

    /**
     * Removes {@link BossBar} from this holder
     *
     * @param bossBar which should be removed
     */
    void removeBossBar(BossBar bossBar);

    /**
     * Returns a list of bossbars of this holder
     *
     * @param includeParents should parent's BossBars be included
     *
     * @return list of player's bossbars
     */
    Collection<? extends BossBar> getBossBars(boolean includeParents);

    default Collection<? extends BossBar> getBossBars()
    {
        return this.getBossBars(false);
    }
}
