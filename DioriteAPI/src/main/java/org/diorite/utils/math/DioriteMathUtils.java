package org.diorite.utils.math;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;

/**
 * Class with some simple math utils/methods.
 */
@SuppressWarnings("MagicNumber")
public final class DioriteMathUtils
{

    private DioriteMathUtils()
    {
    }

    private static final NumberFormat simpleFormat          = new DecimalFormat("###.##");
    private static final NumberFormat simpleFormatForceZero = new DecimalFormat("###.00");

    /**
     * returns string from double formatted to DecimalFormat("###.##")
     *
     * @param d value to format.
     *
     * @return formatted string.
     */
    public static String formatSimpleDecimal(final double d)
    {
        return simpleFormat.format(d);
    }

    /**
     * returns string from double formatted to DecimalFormat("###.00")
     *
     * @param d value to format.
     *
     * @return formatted string.
     */
    public static String formatSimpleDecimalWithZeros(final double d)
    {
        return simpleFormatForceZero.format(d);
    }

    /**
     * Convert given int to String with roman number.
     * Examples:
     * {@code 10 -> X}
     * {@code 99 -> IC}
     * {@code 30 -> XXX}
     * {@code 80 -> LXXX}
     * {@code 1910 -> MCMX}
     * {@code 1903 -> MCMIII}
     *
     * @param i int to convert.
     *
     * @return roman number in string
     */
    public static String toRoman(final int i)
    {
        return RomanNumeral.toString(i);
    }

    /**
     * Convert given string with roman number to int.
     * Examples:
     * {@code X -> 10}
     * {@code LC -> 99}
     * {@code XXX -> 30}
     * {@code LXXX -> 80}
     * {@code MCMX -> 1910}
     * {@code MCMIII -> 1903}
     * <p>
     * It can also convert not really valid numbers, like:
     * {@code IIIIIX -> 5}
     * {@code MDCCCCX -> 1910}
     * {@code MDCDIII -> 1903}
     *
     * @param roman roman numver
     *
     * @return int value of roman number.
     *
     * @throws NumberFormatException if string isn't valid roman number.
     * @see DioriteMathUtils#toRoman(int)
     */
    public static int fromRoman(final String roman) throws NumberFormatException
    {
        return RomanNumeral.toInt(roman);
    }

    /**
     * Return given number if it is in range, or closest value in range.
     * {@code value > max -> max}
     * {@code value < min -> min}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     *
     * @return closest number in range.
     */
    public static byte getInRange(final byte value, final byte min, final byte max)
    {
        if (value > max)
        {
            return max;
        }
        if (value < min)
        {
            return min;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or closest value in range.
     * {@code value > max -> max}
     * {@code value < min -> min}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     *
     * @return closest number in range.
     */
    public static short getInRange(final short value, final short min, final short max)
    {
        if (value > max)
        {
            return max;
        }
        if (value < min)
        {
            return min;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or closest value in range.
     * {@code value > max -> max}
     * {@code value < min -> min}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     *
     * @return closest number in range.
     */
    public static int getInRange(final int value, final int min, final int max)
    {
        if (value > max)
        {
            return max;
        }
        if (value < min)
        {
            return min;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or closest value in range.
     * {@code value > max -> max}
     * {@code value < min -> min}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     *
     * @return closest number in range.
     */
    public static long getInRange(final long value, final long min, final long max)
    {
        if (value > max)
        {
            return max;
        }
        if (value < min)
        {
            return min;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or closest value in range.
     * {@code value > max -> max}
     * {@code value < min -> min}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     *
     * @return closest number in range.
     */
    public static float getInRange(final float value, final float min, final float max)
    {
        if (value > max)
        {
            return max;
        }
        if (value < min)
        {
            return min;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or closest value in range.
     * {@code value > max -> max}
     * {@code value < min -> min}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     *
     * @return closest number in range.
     */
    public static double getInRange(final double value, final double min, final double max)
    {
        if (value > max)
        {
            return max;
        }
        if (value < min)
        {
            return min;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or default value.
     * {@code value > max -> def}
     * {@code value < min -> def}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     * @param def   default value.
     *
     * @return given number or default value.
     */
    public static byte getInRangeOrDefault(final byte value, final byte min, final byte max, final byte def)
    {
        if (value > max)
        {
            return def;
        }
        if (value < min)
        {
            return def;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or default value.
     * {@code value > max -> def}
     * {@code value < min -> def}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     * @param def   default value.
     *
     * @return given number or default value.
     */
    public static short getInRangeOrDefault(final short value, final short min, final short max, final short def)
    {
        if (value > max)
        {
            return def;
        }
        if (value < min)
        {
            return def;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or default value.
     * {@code value > max -> def}
     * {@code value < min -> def}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     * @param def   default value.
     *
     * @return given number or default value.
     */
    public static int getInRangeOrDefault(final int value, final int min, final int max, final int def)
    {
        if (value > max)
        {
            return def;
        }
        if (value < min)
        {
            return def;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or default value.
     * {@code value > max -> def}
     * {@code value < min -> def}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     * @param def   default value.
     *
     * @return given number or default value.
     */
    public static long getInRangeOrDefault(final long value, final long min, final long max, final long def)
    {
        if (value > max)
        {
            return def;
        }
        if (value < min)
        {
            return def;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or default value.
     * {@code value > max -> def}
     * {@code value < min -> def}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     * @param def   default value.
     *
     * @return given number or default value.
     */
    public static float getInRangeOrDefault(final float value, final float min, final float max, final float def)
    {
        if (value > max)
        {
            return def;
        }
        if (value < min)
        {
            return def;
        }
        return value;
    }

    /**
     * Return given number if it is in range, or default value.
     * {@code value > max -> def}
     * {@code value < min -> def}
     * {@code else -> value}
     *
     * @param value number to validate.
     * @param min   min value in range. (inclusive)
     * @param max   max value in range. (inclusive)
     * @param def   default value.
     *
     * @return given number or default value.
     */
    public static double getInRangeOrDefault(final double value, final double min, final double max, final double def)
    {
        if (value > max)
        {
            return def;
        }
        if (value < min)
        {
            return def;
        }
        return value;
    }

    /**
     * Check in number is in between {@link Byte#MIN_VALUE} and {@link Byte#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeByte(final int i)
    {
        return (i >= Byte.MIN_VALUE) && (i <= Byte.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Short#MIN_VALUE} and {@link Short#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeShort(final int i)
    {
        return (i >= Short.MIN_VALUE) && (i <= Short.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Byte#MIN_VALUE} and {@link Byte#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeByte(final long i)
    {
        return (i >= Byte.MIN_VALUE) && (i <= Byte.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Short#MIN_VALUE} and {@link Short#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeShort(final long i)
    {
        return (i >= Short.MIN_VALUE) && (i <= Short.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Byte#MIN_VALUE} and {@link Byte#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeByte(final short i)
    {
        return (i >= Byte.MIN_VALUE) && (i <= Byte.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Integer#MIN_VALUE} and {@link Integer#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeInt(final long i)
    {
        return (i >= Integer.MIN_VALUE) && (i <= Integer.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Long#MIN_VALUE} and {@link Long#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeLong(final float i)
    {
        return (i >= Long.MIN_VALUE) && (i <= Long.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Integer#MIN_VALUE} and {@link Integer#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeInt(final float i)
    {
        return (i >= Integer.MIN_VALUE) && (i <= Integer.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Short#MIN_VALUE} and {@link Short#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeShort(final float i)
    {
        return (i >= Short.MIN_VALUE) && (i <= Short.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Byte#MIN_VALUE} and {@link Byte#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeByte(final float i)
    {
        return (i >= Byte.MIN_VALUE) && (i <= Byte.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Long#MIN_VALUE} and {@link Long#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeLong(final double i)
    {
        return (i >= Long.MIN_VALUE) && (i <= Long.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Integer#MIN_VALUE} and {@link Integer#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeInt(final double i)
    {
        return (i >= Integer.MIN_VALUE) && (i <= Integer.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Short#MIN_VALUE} and {@link Short#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeShort(final double i)
    {
        return (i >= Short.MIN_VALUE) && (i <= Short.MAX_VALUE);
    }

    /**
     * Check in number is in between {@link Byte#MIN_VALUE} and {@link Byte#MAX_VALUE}
     *
     * @param i number to validate.
     *
     * @return true if it is in range.
     */
    public static boolean canBeByte(final double i)
    {
        return (i >= Byte.MIN_VALUE) && (i <= Byte.MAX_VALUE);
    }

    /**
     * Round down given number.
     *
     * @param num number to round down.
     *
     * @return rounded number.
     *
     * @see Math#round(double)
     */
    public static int floor(final double num)
    {
        final int floor = (int) num;
        return (floor == num) ? floor : (floor - (int) (Double.doubleToRawLongBits(num) >>> 63));
    }

    /**
     * Round up given number.
     *
     * @param num number to round up.
     *
     * @return rounded number.
     *
     * @see Math#round(double)
     */
    public static int ceil(final double num)
    {
        final int floor = (int) num;
        return (floor == num) ? floor : (floor + (int) (~ Double.doubleToRawLongBits(num) >>> 63));
    }

    /**
     * Round given number.
     *
     * @param num number to round.
     *
     * @return rounded number.
     *
     * @see Math#round(double)
     */
    public static int round(final double num)
    {
        return floor(num + 0.5d);
    }

    /**
     * Simple square number, just num * num.
     *
     * @param num number to square
     *
     * @return num * num
     */
    public static double square(final double num)
    {
        return num * num;
    }

    /**
     * count 1 bits in nubmer.
     * <p>
     * {@literal 10: 1010 -> 2}
     * {@literal 2: 10 -> 1}
     * {@literal 56: 111000 -> 3}
     * {@literal 34: 100010 -> 2}
     * {@literal 255: 11111111 -> 8}
     * {@literal 256: 100000000 -> 1}
     *
     * @param num number to count bits in it.
     *
     * @return number of 1 bits.
     */
    public static byte countBits(long num)
    {
        byte result;
        for (result = 0; Math.abs(num) > 0; result++)
        {
            num &= num - 1;
        }
        return result;
    }

    /**
     * count 1 bits in nubmer.
     * <p>
     * {@literal 10: 1010 -> 2}
     * {@literal 2: 10 -> 1}
     * {@literal 56: 111000 -> 3}
     * {@literal 34: 100010 -> 2}
     * {@literal 255: 11111111 -> 8}
     * {@literal 256: 100000000 -> 1}
     *
     * @param num number to count bits in it.
     *
     * @return number of 1 bits.
     */
    public static byte countBits(int num)
    {
        byte result;
        for (result = 0; Math.abs(num) > 0; result++)
        {
            num &= num - 1;
        }
        return result;
    }

    /**
     * count 1 bits in nubmer.
     * <p>
     * {@literal 10: 1010 -> 2}
     * {@literal 2: 10 -> 1}
     * {@literal 56: 111000 -> 3}
     * {@literal 34: 100010 -> 2}
     * {@literal 255: 11111111 -> 8}
     * {@literal 256: 100000000 -> 1}
     *
     * @param num number to count bits in it.
     *
     * @return number of 1 bits.
     */
    public static byte countBits(short num)
    {
        byte result;
        for (result = 0; num > 0; result++)
        {
            num &= num - 1;
        }
        return result;
    }

    /**
     * count 1 bits in nubmer.
     * <p>
     * {@literal 10: 1010 -> 2}
     * {@literal 2: 10 -> 1}
     * {@literal 56: 111000 -> 3}
     * {@literal 34: 100010 -> 2}
     * {@literal 255: 11111111 -> 8}
     * {@literal 256: 100000000 -> 1}
     *
     * @param num number to count bits in it.
     *
     * @return number of 1 bits.
     */
    public static byte countBits(char num)
    {
        byte result;
        for (result = 0; num > 0; result++)
        {
            num &= num - 1;
        }
        return result;
    }

    /**
     * count 1 bits in nubmer.
     * <p>
     * {@literal 10: 1010 -> 2}
     * {@literal 2: 10 -> 1}
     * {@literal 56: 111000 -> 3}
     * {@literal 34: 100010 -> 2}
     * {@literal 255: 11111111 -> 8}
     * {@literal 256: 100000000 -> 1}
     *
     * @param num number to count bits in it.
     *
     * @return number of 1 bits.
     */
    public static byte countBits(byte num)
    {
        byte result;
        for (result = 0; num > 0; result++)
        {
            num &= num - 1;
        }
        return result;
    }

    /**
     * Check if given numver is in given range.
     *
     * @param min min value of range (inclusive).
     * @param i   number to validate.
     * @param max max value of range (inclusive).
     *
     * @return true if it is in range.
     */
    public static boolean isBetweenInclusive(final long min, final long i, final long max)
    {
        return (i >= min) && (i <= max);
    }

    /**
     * Check if given numver is in given range.
     *
     * @param min min value of range (exclusive).
     * @param i   number to validate.
     * @param max max value of range (exclusive).
     *
     * @return true if it is in range.
     */
    public static boolean isBetweenExclusive(final long min, final long i, final long max)
    {
        return (i > min) && (i < max);
    }

    /**
     * Check if given numver is in given range.
     *
     * @param min min value of range (inclusive).
     * @param i   number to validate.
     * @param max max value of range (inclusive).
     *
     * @return true if it is in range.
     */
    public static boolean isBetweenInclusive(final double min, final double i, final double max)
    {
        return (i >= min) && (i <= max);
    }

    /**
     * Check if given numver is in given range.
     *
     * @param min min value of range (exclusive).
     * @param i   number to validate.
     * @param max max value of range (exclusive).
     *
     * @return true if it is in range.
     */
    public static boolean isBetweenExclusive(final double min, final double i, final double max)
    {
        return (i > min) && (i < max);
    }

    /**
     * Parse string to int, if string can't be parsed to int, then it will return null.
     *
     * @param str string to parse
     *
     * @return parsed value or null.
     */
    public static Integer asInt(final String str)
    {
        try
        {
            return Integer.valueOf(str);
        } catch (final NumberFormatException e)
        {
            return null;
        }
    }

    /**
     * Parse string to long, if string can't be parsed to long, then it will return null.
     *
     * @param str string to parse
     *
     * @return parsed value or null.
     */
    public static Long asLong(final String str)
    {
        try
        {
            return Long.valueOf(str);
        } catch (final NumberFormatException e)
        {
            return null;
        }
    }

    /**
     * Parse string to double, if string can't be parsed to double, then it will return null.
     *
     * @param str string to parse
     *
     * @return parsed value or null.
     */
    public static Double asDouble(final String str)
    {
        try
        {
            return Double.valueOf(str);
        } catch (final NumberFormatException e)
        {
            return null;
        }
    }

    /**
     * Parse string to float, if string can't be parsed to float, then it will return null.
     *
     * @param str string to parse
     *
     * @return parsed value or null.
     */
    public static Float asFloat(final String str)
    {
        try
        {
            return Float.valueOf(str);
        } catch (final NumberFormatException e)
        {
            return null;
        }
    }

    /**
     * Parse string to int, if string can't be parsed to int, then it will return given default value.
     *
     * @param str string to parse
     * @param def default value.
     *
     * @return parsed value or default value.
     */
    public static int asInt(final String str, final int def)
    {
        try
        {
            return Integer.parseInt(str);
        } catch (final NumberFormatException e)
        {
            return def;
        }
    }

    /**
     * Parse string to long, if string can't be parsed to long, then it will return given default value.
     *
     * @param str string to parse
     * @param def default value.
     *
     * @return parsed value or default value.
     */
    public static long asLong(final String str, final long def)
    {
        try
        {
            return Long.parseLong(str);
        } catch (final NumberFormatException e)
        {
            return def;
        }
    }

    /**
     * Parse string to double, if string can't be parsed to double, then it will return given default value.
     *
     * @param str string to parse
     * @param def default value.
     *
     * @return parsed value or default value.
     */
    public static double asDouble(final String str, final double def)
    {
        try
        {
            return Double.parseDouble(str);
        } catch (final NumberFormatException e)
        {
            return def;
        }
    }

    /**
     * Parse string to float, if string can't be parsed to float, then it will return given default value.
     *
     * @param str string to parse
     * @param def default value.
     *
     * @return parsed value or default value.
     */
    public static float asFloat(final String str, final float def)
    {
        try
        {
            return Float.parseFloat(str);
        } catch (final NumberFormatException e)
        {
            return def;
        }
    }

    /**
     * Simple parse boolean.
     *
     * @param str string to parse
     *
     * @return parsed value
     *
     * @see Boolean#parseBoolean(String)
     */
    public static boolean asBoolean(final String str)
    {
        return Boolean.parseBoolean(str);
    }

    /**
     * Parse string to boolean using two collections of words, for true and false values.
     * If any of trueWords is equals (equalsIgnoreCase) to given string, then method returns ture.
     * If any of falseWords is equals (equalsIgnoreCase) to given string, then method returns false.
     * If given word don't match any words from collections, then method returns null
     *
     * @param str        string to parse.
     * @param trueWords  words that mean "true"
     * @param falseWords words that mean "false"
     *
     * @return true/false or null.
     */
    public static Boolean asBoolean(final String str, final Collection<String> trueWords, final Collection<String> falseWords)
    {
        if (trueWords.stream().anyMatch(s -> s.equalsIgnoreCase(str)))
        {
            return Boolean.TRUE;
        }
        if (falseWords.stream().anyMatch(s -> s.equalsIgnoreCase(str)))
        {
            return Boolean.FALSE;
        }
        return null;
    }
}
