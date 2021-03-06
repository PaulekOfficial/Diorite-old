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

import org.diorite.chat.component.BaseComponent;
import org.diorite.entity.pathfinder.PathfinderService;
import org.diorite.event.Event;
import org.diorite.event.EventType;
import org.diorite.event.pipelines.EventPipeline;
import org.diorite.inventory.recipe.craft.CraftingRecipeManager;
import org.diorite.permissions.PermissionsManager;

/**
 * Interface used to manage all event pipelines, other pipelines, plugins and more.
 */
public interface ServerManager
{
    /**
     * Method will find event type of this event and pass given event to its pipeline. <br>
     * If it can't find event type or pipeline is null, method will return false.
     *
     * @param event event to call.
     *
     * @return true if pipeline was found and event was passed to it.
     */
    default boolean callEvent(final Event event)
    {
        return EventType.callEvent(event);
    }

    /**
     * Construct new event type and register it.
     *
     * @param eventClass    class of used event.
     * @param pipelineClass class of used pipeline.
     * @param pipeline      event pipeline.
     * @param <T>           type of event.
     * @param <E>           type of pipeline for this event.
     *
     * @return created and registered event type.
     *
     * @throws IllegalArgumentException if event class is already registered.
     */
    default <T extends Event, E extends EventPipeline<T>> EventType<T, E> register(final Class<T> eventClass, final Class<E> pipelineClass, final E pipeline) throws IllegalArgumentException
    {
        return EventType.register(eventClass, pipelineClass, pipeline);
    }

    /**
     * Returns current permission manager, returned value should not be cached. (only to local variables)
     *
     * @return current permission manager.
     */
    PermissionsManager getPermissionsManager();

    /**
     * Change {@link PermissionsManager} used by this server. <br>
     * PermissionsManager is center point of permission system where all permissions/groups are stored,
     * data is loaded etc...
     *
     * @param permissionsManager new permissions manager for server.
     */
    void setPermissionsManager(PermissionsManager permissionsManager);

    /**
     * Returns recipe manager for this server.
     *
     * @return recipe manager for this server.
     */
    CraftingRecipeManager getRecipeManager();

    /**
     * Returns entity factory that can be used to create new entites.
     *
     * @return entity factory that can be used to create new entites.
     */
    EntityFactory getEntityFactory();

    /**
     * Returns a pathfinder service what is currently used by server
     *
     * @return currently used pathfinder service
     */
    PathfinderService getPathfinderService();

    /**
     * Changes a currently used pathfinder service
     *
     * @param pathfinderService new pathfinder service
     */
    void setPathfinderService(PathfinderService pathfinderService);

    /**
     * Creates a new boss bar instance with random UUID
     *
     * @return new boss bar instance
     *
     * @see BossBar
     */
    BossBar createBossBar(int health, BaseComponent title, BossBar.Style style, BossBar.Color color);
}
