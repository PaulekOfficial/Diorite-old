package diorite.material.blocks;

import java.util.Map;

import diorite.material.BlockMaterialData;
import diorite.utils.collections.SimpleStringHashMap;
import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;

public class Dirt extends BlockMaterialData
{
    public static final byte USED_DATA_VALUES = 3;

    public static final Dirt DIRT             = new Dirt();
    public static final Dirt DIRT_COARSE_DIRT = new Dirt("COARSE_DIRT", 0x01);
    public static final Dirt DIRT_PODZOL      = new Dirt("PODZOL", 0x02);

    private static final Map<String, Dirt>    byName = new SimpleStringHashMap<>(USED_DATA_VALUES, SLOW_GROW);
    private static final TByteObjectMap<Dirt> byID   = new TByteObjectHashMap<>(USED_DATA_VALUES, SLOW_GROW);

    protected Dirt()
    {
        super("DIRT", 3, "DIRT", (byte) 0x00);
    }

    public Dirt(final String enumName, final int type)
    {
        super(DIRT.name(), DIRT.getId(), enumName, (byte) type);
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public float getBlastResistance()
    {
        return 2.5f;
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public float getHardness()
    {
        return 0.5f;
    }

    public Dirt(final int maxStack, final String typeName, final byte type)
    {
        super(DIRT.name(), DIRT.getId(), maxStack, typeName, type);
    }

    @Override
    public BlockMaterialData getType(final String name)
    {
        return getByEnumName(name);
    }

    @Override
    public BlockMaterialData getType(final int id)
    {
        return getByID(id);
    }

    public static Dirt getByID(final int id)
    {
        return byID.get((byte) id);
    }

    public static Dirt getByEnumName(final String name)
    {
        return byName.get(name);
    }

    public static void register(final Dirt element)
    {
        byID.put((byte) element.getId(), element);
        byName.put(element.name(), element);
    }

    static
    {
        Dirt.register(DIRT);
        Dirt.register(DIRT_COARSE_DIRT);
        Dirt.register(DIRT_PODZOL);
    }
}
