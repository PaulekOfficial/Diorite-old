package org.diorite.impl.permissions.pattern;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.permissions.Permission;
import org.diorite.permissions.PermissionLevel;
import org.diorite.permissions.pattern.PermissionPattern;

/**
 * Basic abstract permission pattern implementing only permissions map,
 *
 * @see PermissionPattern#getPermissions()
 */
public abstract class AbstractPermissionPattern implements PermissionPattern
{
    /**
     * This pattern child permissions.
     */
    protected Map<Permission, PermissionLevel> permissions;

    @Override
    public Map<Permission, PermissionLevel> getPermissions()
    {
        if (this.permissions == null)
        {
            this.permissions = new HashMap<>(16, .4f);
        }
        return this.permissions;
    }

    @Override
    public boolean containsPermissions()
    {
        return (this.permissions != null) && ! this.permissions.isEmpty();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("permissions", this.permissions).toString();
    }
}