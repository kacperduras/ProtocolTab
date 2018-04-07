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
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.apache.commons.lang.Validate;

public class PlayerInfoHeaderFooterPacket extends Packet {

    public static final PacketType TYPE = PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER;

    public PlayerInfoHeaderFooterPacket() {
        super(new PacketContainer(TYPE), TYPE);
    }

    public PlayerInfoHeaderFooterPacket(PacketContainer packet) {
        super(packet, TYPE);
    }

    public WrappedChatComponent getHeader() {
        return this.getHandle().getChatComponents().read(0);
    }

    public WrappedChatComponent getFooter() {
        return this.getHandle().getChatComponents().read(1);
    }

    public void setHeader(WrappedChatComponent value) {
        Validate.isTrue(value != null, "Value cannot be null!");

        this.getHandle().getChatComponents().write(0, value);
    }

    public void setFooter(WrappedChatComponent value) {
        Validate.isTrue(value != null, "Value cannot be null!");

        this.getHandle().getChatComponents().write(1, value);
    }

}
