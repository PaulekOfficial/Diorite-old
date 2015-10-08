package org.diorite.material.items.tool.basic;

import java.util.Map;

import org.diorite.material.BasicToolData;
import org.diorite.material.items.tool.BasicToolMat;
import org.diorite.utils.collections.maps.CaseInsensitiveMap;
import org.diorite.utils.lazy.LazyValue;
import org.diorite.utils.math.DioriteMathUtils;

import gnu.trove.map.TShortObjectMap;
import gnu.trove.map.hash.TShortObjectHashMap;

@SuppressWarnings("ClassHasNoToStringMethod")
public class ShearsMat extends BasicToolMat
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 1;

    public static final ShearsMat SHEARS = new ShearsMat();

    private static final Map<String, ShearsMat>     byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TShortObjectMap<ShearsMat> byID   = new TShortObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR, Short.MIN_VALUE);

    protected final LazyValue<ShearsMat> next = new LazyValue<>(() -> (this.haveValidDurability()) ? getByDurability(this.getDurability() + 1) : null);
    protected final LazyValue<ShearsMat> prev = new LazyValue<>(() -> (this.haveValidDurability()) ? getByDurability(this.getDurability() - 1) : null);

    @SuppressWarnings("MagicNumber")
    protected ShearsMat()
    {
        super("SHEARS", 359, "minecraft:shears", "NEW", (short) 0x00, new BasicToolData(238));
    }

    protected ShearsMat(final int durability)
    {
        super(SHEARS.name(), SHEARS.getId(), SHEARS.getMinecraftId(), Integer.toString(durability), (short) durability, new BasicToolData(SHEARS.getToolData()));
    }

    public ShearsMat(final String enumName, final int id, final String minecraftId, final String typeName, final short type, final BasicToolData toolData)
    {
        super(enumName, id, minecraftId, typeName, type, toolData);
    }

    public ShearsMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final short type, final BasicToolData toolData)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type, toolData);
    }

    @Override
    public ShearsMat getType(final String type)
    {
        return getByEnumName(type);
    }

    @Override
    public ShearsMat getType(final int type)
    {
        return getByID(type);
    }

    @Override
    public ShearsMat increaseDurability()
    {
        return this.next.get();
    }

    @Override
    public ShearsMat decreaseDurability()
    {
        return this.prev.get();
    }

    @Override
    public ShearsMat setDurability(final int durability)
    {
        return this.getType(durability);
    }

    @Override
    public ShearsMat[] types()
    {
        return shearsTypes();
    }

    /**
     * Returns one of Shears sub-type based on sub-id.
     *
     * @param id sub-type id
     *
     * @return sub-type of Shears.
     */
    public static ShearsMat getByID(final int id)
    {
        ShearsMat mat = byID.get((short) id);
        if (mat == null)
        {
            mat = new ShearsMat(id);
            if ((id > 0) && (id < SHEARS.getBaseDurability()))
            {
                ShearsMat.register(mat);
            }
        }
        return mat;
    }

    /**
     * Returns one of Shears sub-type based on durability.
     *
     * @param id durability of type.
     *
     * @return sub-type of Shears.
     */
    public static ShearsMat getByDurability(final int id)
    {
        return getByID(id);
    }

    /**
     * Returns one of Shears-type based on name (selected by diorite team).
     * If block contains only one type, sub-name of it will be this same as name of material.<br>
     * Returns null if name can't be parsed to int and it isn't "NEW" one.
     *
     * @param name name of sub-type
     *
     * @return sub-type of Shears.
     */
    public static ShearsMat getByEnumName(final String name)
    {
        final ShearsMat mat = byName.get(name);
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
    public static void register(final ShearsMat element)
    {
        byID.put(element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    /**
     * @return array that contains all sub-types of this item.
     */
    public static ShearsMat[] shearsTypes()
    {
        return new ShearsMat[]{SHEARS};
    }

    static
    {
        ShearsMat.register(SHEARS);
    }
}

