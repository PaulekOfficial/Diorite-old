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

package org.diorite.plugin;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.diorite.Core;

public abstract class DioritePlugin implements BasePlugin
{
    private Logger            logger;
    private PluginClassLoader classLoader;
    private PluginLoader      pluginLoader;
    private boolean           initialised;
    private boolean           enabled;
    private PluginData        pluginData;
    private File              pluginFolder;
    private File              dataFolder;

    protected DioritePlugin()
    {
    }

    /**
     * Invokes very early, at end of {@link Core} constructor
     */
    @Override
    public void onLoad()
    {
    }

    /**
     * Invokes before world load
     */
    @Override
    public void onEnable()
    {
    }

    @Override
    public void onDisable()
    {
    }

    @Override
    public final String getName()
    {
        this.initCheck();
        return this.pluginData.getName();
    }

    @Override
    public String getPrefix()
    {
        this.initCheck();
        return this.pluginData.getPrefix();
    }

    @Override
    public final String getVersion()
    {
        this.initCheck();
        return this.pluginData.getVersion();
    }

    @Override
    public final String getAuthor()
    {
        this.initCheck();
        return this.pluginData.getAuthor();
    }

    @Override
    public String getDescription()
    {
        this.initCheck();
        return this.pluginData.getDescription();
    }

    @Override
    public String getWebsite()
    {
        this.initCheck();
        return this.pluginData.getWebsite();
    }

    @Override
    public String getBasePackage()
    {
        this.initCheck();
        return this.pluginData.getBasePackage();
    }

    @Override
    public Logger getLogger()
    {
        this.initCheck();
        if (this.logger == null)
        {
            this.logger = LoggerFactory.getLogger("[" + this.getPrefix() + "]");
        }
        return this.logger;
    }

    @Override
    public final PluginLoader getPluginLoader()
    {
        this.initCheck();
        return this.pluginLoader;
    }

    @Override
    public File getDataFolder()
    {
        this.initCheck();
        if (this.dataFolder == null)
        {
            this.dataFolder = new File(this.pluginFolder, this.getName());
        }
        return this.dataFolder;
    }

    @Override
    public final void init(final File pluginsDirectory, final PluginClassLoader classLoader, final PluginLoader pluginLoader, final PluginDataBuilder data)
    {
        if (this.initialised)
        {
            throw new RuntimeException("Internal method");
        }
        if (StringUtils.isEmpty(data.getBasePackage()))
        {
            data.setBasePackage(this.getClass().getPackage().getName());
        }
        else if (Package.getPackage(data.getBasePackage()) == null)
        {
            throw new IllegalArgumentException("Specified base package " + data.getBasePackage() + " doesn't exists.");
        }
        this.pluginFolder = pluginsDirectory;
        this.classLoader = classLoader;
        this.pluginLoader = pluginLoader;
        this.pluginData = data.build();
        this.initialised = true;
    }

    @Override
    public final void setEnabled(final boolean enabled)
    {
        this.initCheck();

        if (enabled == this.enabled)
        {
            throw new IllegalArgumentException();
        }

        this.enabled = enabled;
        if (enabled)
        {
            this.logger.info("Enabling " + this.getFullName());
            this.onEnable();
        }
        else
        {
            this.logger.info("Disabling " + this.getFullName());
            this.onDisable();
        }
    }

    @Override
    public boolean isInitialised()
    {
        return this.initialised;
    }

    @Override
    public final boolean isEnabled()
    {
        this.initCheck();
        return this.enabled;
    }

    /**
     * Returns plugin class loader.
     *
     * @return plugin class loader.
     *
     * @see PluginClassLoader
     */
    @Override
    public final PluginClassLoader getClassLoader()
    {
        this.initCheck();
        return this.classLoader;
    }

    private void initCheck()
    {
        if (! this.initialised)
        {
            throw new IllegalStateException("Plugin isn't initialised");
        }
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("classLoader", this.classLoader).append("pluginLoader", this.pluginLoader).append("initialised", this.initialised).append("enabled", this.enabled).append("pluginData", this.pluginData).append("pluginFolder", this.pluginFolder).append("dataFolder", this.dataFolder).toString();
    }
}
