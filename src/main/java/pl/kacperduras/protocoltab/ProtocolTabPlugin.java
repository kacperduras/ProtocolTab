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

import ch.njol.skript.Skript;
import org.bukkit.plugin.java.JavaPlugin;
import pl.kacperduras.protocoltab.manager.ProtocolTabManager;

import java.io.IOException;

public final class ProtocolTabPlugin extends JavaPlugin {

    private static ProtocolTabPlugin instance;

    private ProtocolTabManager manager;

    @Override
    public void onLoad() {
        this.saveDefaultConfig();

        instance = this;
    }

    @Override
    public void onEnable() {
        this.manager = new ProtocolTabManager(this.getConfig().getInt("default-ping", 0));

        // Skript hook
        if (this.getServer().getPluginManager().getPlugin("Skript") != null && Skript.isAcceptRegistrations()) {
            try {
                Skript.registerAddon(this).loadClasses("pl.kacperduras.protocoltab", "skript");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {
    }

    protected static ProtocolTabPlugin getInstance() {
        return instance;
    }

    protected ProtocolTabManager getManager() {
        return manager;
    }

}
