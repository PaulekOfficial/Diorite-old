package org.diorite.permissions.pattern;

import java.util.Map;

import org.diorite.permissions.Permissible;
import org.diorite.permissions.Permission;
import org.diorite.permissions.PermissionLevel;

public interface PermissionPattern
{
    /**
     * Checks if given string can be a permission using this pattern. <br>
     * Like "foo.5" will return true for "foo.{$++}" or "foo.{$--}" pattern, but "foo.5a" will return false.
     *
     * @param str string/permission to check.
     *
     * @return if given string is valid for this pattern.
     */
    boolean isValid(String str);

    /**
     * Returns string representation of this pattern.
     *
     * @return string representation of this pattern.
     */
    String getValue();

    /**
     * Returns children permissions for this permission pattern. <br>
     * If you edit this map you will need call {@link Permissible#recalculatePermissions()}
     *
     * @return children permissions for this permission pattern.
     */
    Map<Permission, PermissionLevel> getPermissions();

    /**
     * Returns true if this permissions contains any child permissions.
     *
     * @return true if this permissions contains any child permissions.
     */
    boolean containsPermissions();
}
