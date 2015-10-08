package org.diorite.material.items.tool.tool;

import java.util.Map;

import org.diorite.material.ToolMaterial;
import org.diorite.material.ToolType;
import org.diorite.utils.collections.maps.CaseInsensitiveMap;
import org.diorite.utils.lazy.LazyValue;
import org.diorite.utils.math.DioriteMathUtils;

import gnu.trove.map.TShortObjectMap;
import gnu.trove.map.hash.TShortObjectHashMap;

@SuppressWarnings("ClassHasNoToStringMethod")
public class IronShovelMat extends ShovelMat
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 1;

    public static final IronShovelMat IRON_SHOVEL = new IronShovelMat();

    private static final Map<String, IronShovelMat>     byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TShortObjectMap<IronShovelMat> byID   = new TShortObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR, Short.MIN_VALUE);

    protected final LazyValue<IronShovelMat> next = new LazyValue<>(() -> (this.haveValidDurability()) ? getByDurability(this.getDurability() + 1) : null);
    protected final LazyValue<IronShovelMat> prev = new LazyValue<>(() -> (this.haveValidDurability()) ? getByDurability(this.getDurability() - 1) : null);

    @SuppressWarnings("MagicNumber")
    protected IronShovelMat()
    {
        super("IRON_SHOVEL", 256, "minecraft:iron_Shovel", "NEW", (short) 0, ToolMaterial.IRON);
    }

    protected IronShovelMat(final int durability)
    {
        super(IRON_SHOVEL.name(), IRON_SHOVEL.getId(), IRON_SHOVEL.getMinecraftId(), Integer.toString(durability), (short) durability, ToolMaterial.IRON);
    }

    protected IronShovelMat(final String enumName, final int id, final String minecraftId, final String typeName, final short type, final ToolMaterial toolMaterial, final ToolType toolType)
    {
        super(enumName, id, minecraftId, typeName, type, toolMaterial, toolType);
    }

    protected IronShovelMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final short type, final ToolMaterial toolMaterial, final ToolType toolType)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type, toolMaterial, toolType);
    }

    @Override
    public IronShovelMat[] types()
    {
        return new IronShovelMat[]{IRON_SHOVEL};
    }

    @Override
    public IronShovelMat getType(final String type)
    {
        return getByEnumName(type);
    }

    @Override
    public IronShovelMat getType(final int type)
    {
        return getByID(type);
    }

    @Override
    public IronShovelMat increaseDurability()
    {
        return this.next.get();
    }

    @Override
    public IronShovelMat decreaseDurability()
    {
        return this.prev.get();
    }

    @Override
    public IronShovelMat setDurability(final int durability)
    {
        return this.getType(durability);
    }

    /**
     * Returns one of IronShovel sub-type based on sub-id.
     *
     * @param id sub-type id
     *
     * @return sub-type of IronShovel.
     */
    public static IronShovelMat getByID(final int id)
    {
        IronShovelMat mat = byID.get((short) id);
        if (mat == null)
        {
            mat = new IronShovelMat(id);
            if ((id > 0) && (id < IRON_SHOVEL.getBaseDurability()))
            {
                IronShovelMat.register(mat);
            }
        }
        return mat;
    }

    /**
     * Returns one of IronShovel sub-type based on durability.
     *
     * @param id durability of type.
     *
     * @return sub-type of IronShovel.
     */
    public static IronShovelMat getByDurability(final int id)
    {
        return getByID(id);
    }

    /**
     * Returns one of IronShovel-type based on name (selected by diorite team).
     * If block contains only one type, sub-name of it will be this same as name of material.<br>
     * Returns null if name can't be parsed to int and it isn't "NEW" one.
     *
     * @param name name of sub-type
     *
     * @return sub-type of IronShovel.
     */
    public static IronShovelMat getByEnumName(final String name)
    {
        final IronShovelMat mat = byName.get(name);
        if (mat == null)
        {
            final Integer idType = DioriteMathUtils.asInt(name);
            if (idType == null)
            {
                return null;
            }
            return getByID(idType);
        }
        return mat;
    }

    /**
     * Register new sub-type, may replace existing sub-types.
     * Should be used only if you know what are you doing, it will not create fully usable material.
     *
     * @param element sub-type to register
     */
    public static void register(final IronShovelMat element)
    {
        byID.put(element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    /**
     * @return array that contains all sub-types of this block.
     */
    public static IronShovelMat[] ironShovelTypes()
    {
        return new IronShovelMat[]{IRON_SHOVEL};
    }

    static
    {
        IronShovelMat.register(IRON_SHOVEL);
    }
}