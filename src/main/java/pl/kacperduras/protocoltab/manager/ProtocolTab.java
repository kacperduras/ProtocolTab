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
package pl.kacperduras.protocoltab.manager;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import net.md_5.bungee.api.chat.BaseComponent;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.kacperduras.protocoltab.packet.PlayerInfoHeaderFooterPacket;
import pl.kacperduras.protocoltab.packet.PlayerInfoPacket;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ProtocolTab {

    public final static String BLANK_TEXT = " ";
    public final static char[] ALPHABET = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T'};

    private final Map<Integer, ProtocolSlot> slots = new ConcurrentHashMap<>(80);
    private final UUID uuid;

    private int ping;
    private String header;
    private String footer;

    public ProtocolTab(UUID uuid, int ping) {
        Validate.isTrue(uuid != null, "UUID cannot be null!");

        this.uuid = uuid;
        this.ping = ping;
    }

    public void update(Player player) {
        if (player == null || !player.isOnline()) {
            return;
        }

        // tab list slots
        PlayerInfoPacket infoPacket = new PlayerInfoPacket();
        infoPacket.setAction(EnumWrappers.PlayerInfoAction.ADD_PLAYER);

        List<PlayerInfoData> infoData = new ArrayList<>();
        for (Map.Entry<Integer, ProtocolSlot> entry : this.slots.entrySet()) {
            infoData.add(
                    new PlayerInfoData(entry.getValue().getProfile(), ping, EnumWrappers.NativeGameMode.NOT_SET,
                                              WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes(
                                                      '&', entry.getValue().getText())))
            );
        }

        infoPacket.setData(infoData);
        infoPacket.sendPacket(player);

        // header and footer
        PlayerInfoHeaderFooterPacket packet = new PlayerInfoHeaderFooterPacket();
        if (this.header == null) {
            this.header = "";
        }
        if (this.footer == null) {
            this.footer = "";
        }

        packet.setHeader(WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes(
                '&', this.header)));
        packet.setFooter(WrappedChatComponent.fromText(ChatColor.translateAlternateColorCodes(
                '&', this.footer)));
        packet.sendPacket(player);
    }

    public void update() {
        Player player = Bukkit.getPlayer(this.uuid);
        if (player == null) {
            return;
        }

        this.update(player);
    }

    public ProtocolSlot getSlot(int index) {
        return this.slots.computeIfAbsent(index, key -> new ProtocolSlot(
                new WrappedGameProfile(UUID.randomUUID(), "!" + this.getSlotName(index)), BLANK_TEXT));
    }

    public int getPing() {
        return ping;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public void setSlot(int index, String text) {
        Validate.isTrue(text != null, "Text cannot be null!");

        this.getSlot(index).setText(text);
    }

    public void setText(int index, BaseComponent... component) {
        Validate.isTrue(component != null);

        this.getSlot(index).setText(component);
    }

    public void setPing(int ping) {
        Validate.isTrue(ping < 0, "Ping cannot be null!");

        this.ping = ping;
    }

    public void setHeader(String header) {
        Validate.isTrue(header != null, "Header cannot be null!");

        this.header = header;
    }

    public void setFooter(String footer) {
        Validate.isTrue(footer != null, "Footer cannot be null!");

        this.footer = footer;
    }

    public void setHeader(BaseComponent... component) {
        Validate.isTrue(component != null, "Component cannot be null!");

        this.setFooter(BaseComponent.toLegacyText(component));
    }

    public void setFooter(BaseComponent... component) {
        Validate.isTrue(component != null, "Component cannot be null!");

        this.setFooter(BaseComponent.toLegacyText(component));
    }

    private String getSlotName(int index) {
        if (index < 20) {
            return "A-" + ALPHABET[index];
        }
        else if (index < 40) {
            return "B-" + ALPHABET[index - 20];
        }
        else if (index < 60) {
            return "C-" + ALPHABET[index - 40];
        }
        else if (index < 80) {
            return "D-" + ALPHABET[index - 60];
        }
        else {
            return "";
        }
    }

}
