/*
   Copyright 2017-2018 Kacper Duras

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package pl.kacperduras.protocoltab.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import org.apache.commons.lang.Validate;

import java.util.List;

public class PlayerInfoPacket extends Packet {

    public final static PacketType TYPE = PacketType.Play.Server.PLAYER_INFO;

    public PlayerInfoPacket() {
        super(new PacketContainer(TYPE), TYPE);
    }

    public PlayerInfoPacket(PacketContainer packet) {
        super(packet, TYPE);
    }

    public EnumWrappers.PlayerInfoAction getAction() {
        return this.getHandle().getPlayerInfoAction().read(0);
    }

    public List<PlayerInfoData> getData() {
        return this.getHandle().getPlayerInfoDataLists().read(0);
    }

    public void setAction(EnumWrappers.PlayerInfoAction value) {
        Validate.isTrue(value != null, "Value cannot be null!");

        this.getHandle().getPlayerInfoAction().write(0, value);
    }

    public void setData(List<PlayerInfoData> value) {
        Validate.isTrue(value != null, "Value cannot be null!");

        this.getHandle().getPlayerInfoDataLists().write(0, value);
    }

}
