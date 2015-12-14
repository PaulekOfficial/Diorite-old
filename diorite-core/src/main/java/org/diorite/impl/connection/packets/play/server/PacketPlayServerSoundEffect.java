/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Diorite (by Bartłomiej Mazur (aka GotoFinal))
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

package org.diorite.impl.connection.packets.play.server;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.impl.connection.EnumProtocol;
import org.diorite.impl.connection.EnumProtocolDirection;
import org.diorite.impl.connection.packets.PacketClass;
import org.diorite.impl.connection.packets.PacketDataSerializer;
import org.diorite.impl.connection.packets.play.PacketPlayServerListener;
import org.diorite.BlockLocation;
import org.diorite.ILocation;
import org.diorite.Sound;

@SuppressWarnings("MultiplyOrDivideByPowerOfTwo")
@PacketClass(id = 0x47, protocol = EnumProtocol.PLAY, direction = EnumProtocolDirection.CLIENTBOUND, size = 22)
public class PacketPlayServerSoundEffect extends PacketPlayServer
{
    private Sound sound; // 1-5 bytes
    private int   x; // 4 bytes
    private int   y; // 4 bytes
    private int   z; // 4 bytes
    private float volume; // 4 bytes
    private int  pitch; // 1 byte

    public PacketPlayServerSoundEffect()
    {
    }

    public PacketPlayServerSoundEffect(final Sound sound, final int x, final int y, final int z, final float volume, final int pitch)
    {
        this.sound = sound;
        this.x = x;
        this.y = y;
        this.z = z;
        this.volume = volume;
        this.pitch = pitch;
    }

    public PacketPlayServerSoundEffect(final Sound sound, final BlockLocation location, final float volume, final int pitch)
    {
        this.sound = sound;
        this.x = location.getX() * 8;
        this.y = location.getY() * 8;
        this.z = location.getZ() * 8;
        this.volume = volume;
        this.pitch = pitch;
    }

    public PacketPlayServerSoundEffect(final Sound sound, final ILocation location, final float volume, final int pitch)
    {
        this.sound = sound;
        this.x = (int) (location.getX() * 8);
        this.y = (int) (location.getY() * 8);
        this.z = (int) (location.getZ() * 8);
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public void readPacket(final PacketDataSerializer data) throws IOException
    {
        this.sound = Sound.getById(data.readVarInt());
        this.x = data.readInt();
        this.y = data.readInt();
        this.z = data.readInt();
        this.volume = data.readFloat();
        this.pitch = data.readUnsignedByte();
    }

    @Override
    public void writeFields(final PacketDataSerializer data) throws IOException
    {
        data.writeVarInt(this.sound.getId());
        data.writeInt(this.x);
        data.writeInt(this.y);
        data.writeInt(this.z);
        data.writeFloat(this.volume);
        data.writeByte(this.pitch);
    }

    public Sound getSound()
    {
        return this.sound;
    }

    public void setSound(final Sound sound)
    {
        this.sound = sound;
    }

    public int getX()
    {
        return this.x;
    }

    public void setX(final int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setY(final int y)
    {
        this.y = y;
    }

    public int getZ()
    {
        return this.z;
    }

    public void setZ(final int z)
    {
        this.z = z;
    }

    public float getVolume()
    {
        return this.volume;
    }

    public void setVolume(final float volume)
    {
        this.volume = volume;
    }

    public int getPitch()
    {
        return this.pitch;
    }

    public void setPitch(final int pitch)
    {
        this.pitch = pitch;
    }

    @Override
    public void handle(final PacketPlayServerListener listener)
    {
        listener.handle(this);
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("sound", this.sound).append("x", this.x).append("y", this.y).append("z", this.z).append("volume", this.volume).append("pitch", this.pitch).toString();
    }
}
