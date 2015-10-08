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
public class BowMat extends BasicToolMat
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 1;

    public static final BowMat BOW = new BowMat();

    private static final Map<String, BowMat>     byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final TShortObjectMap<BowMat> byID   = new TShortObjectHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR, Short.MIN_VALUE);

    protected final LazyValue<BowMat> next = new LazyValue<>(() -> (this.haveValidDurability()) ? getByDurability(this.getDurability() + 1) : null);
    protected final LazyValue<BowMat> prev = new LazyValue<>(() -> (this.haveValidDurability()) ? getByDurability(this.getDurability() - 1) : null);

    @SuppressWarnings("MagicNumber")
    protected BowMat()
    {
        super("BOW", 261, "minecraft:bow", "NEW", (short) 0x00, new BasicToolData(385));
    }

    protected BowMat(final int durability)
    {
        super(BOW.name(), BOW.getId(), BOW.getMinecraftId(), Integer.toString(durability), (short) durability, new BasicToolData(BOW.getToolData()));
    }

    public BowMat(final String enumName, final int id, final String minecraftId, final String typeName, final short type, final BasicToolData toolData)
    {
        super(enumName, id, minecraftId, typeName, type, toolData);
    }

    public BowMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final short type, final BasicToolData toolData)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type, toolData);
    }

    @Override
    public BowMat getType(final String type)
    {
        return getByEnumName(type);
    }

    @Override
    public BowMat getType(final int type)
    {
        return getByID(type);
    }

    @Override
    public BowMat increaseDurability()
    {
        return this.next.get();
    }

    @Override
    public BowMat decreaseDurability()
    {
        return this.prev.get();
    }

    @Override
    public BowMat setDurability(final int durability)
    {
        return this.getType(durability);
    }

    @Override
    public BowMat[] types()
    {
        return bowTypes();
    }

    /**
     * Returns one of Bow sub-type based on sub-id.
     *
     * @param id sub-type id
     *
     * @return sub-type of Bow.
     */
    public static BowMat getByID(final int id)
    {
        BowMat mat = byID.get((short) id);
        if (mat == null)
        {
            mat = new BowMat(id);
            if ((id > 0) && (id < BOW.getBaseDurability()))
            {
                BowMat.register(mat);
            }
        }
        return mat;
    }

    /**
     * Returns one of Bow sub-type based on durability.
     *
     * @param id durability of type.
     *
     * @return sub-type of Bow.
     */
    public static BowMat getByDurability(final int id)
    {
        return getByID(id);
    }

    /**
     * Returns one of Bow-type based on name (selected by diorite team).
     * If block contains only one type, sub-name of it will be this same as name of material.<br>
     * Returns null if name can't be parsed to int and it isn't "NEW" one.
     *
     * @param name name of sub-type
     *
     * @return sub-type of Bow.
     */
    public static BowMat getByEnumName(final String name)
    {
        final BowMat mat = byName.get(name);
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
    public static void register(final BowMat element)
    {
        byID.put(element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    /**
     * @return array that contains all sub-types of this item.
     */
    public static BowMat[] bowTypes()
    {
        return new BowMat[]{BOW};
    }

    static
    {
        BowMat.register(BOW);
    }
}

