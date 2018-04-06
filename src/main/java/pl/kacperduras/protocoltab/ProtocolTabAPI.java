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
package pl.kacperduras.protocoltab;

import org.bukkit.entity.Player;
import pl.kacperduras.protocoltab.manager.ProtocolTab;

import java.util.UUID;

public final class ProtocolTabAPI {

    public static ProtocolTab getTablist(Player player) {
        return ProtocolTabPlugin.getInstance().getManager().getTablist(player);
    }

    public static ProtocolTab getTablist(UUID uuid) {
        return ProtocolTabPlugin.getInstance().getManager().getTablist(uuid);
    }

}
