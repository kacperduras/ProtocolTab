# ProtocolTab [![Build Status](https://travis-ci.org/kacperduras/ProtocolTab.svg?branch=master)](https://travis-ci.org/kacperduras/ProtocolTab)

ProtocolTab is a small, easy and fast in use api for managing tab list on your Minecraft server.
 Each player can see different tab list, and everything is limited to your imagination.

## Maven
```xml
<repository>
    <id>kacperduras-repo</id>
    <url>https://repo.kacperduras.pl/content/groups/public/</url>
</repository>

<dependency>
    <groupId>pl.kacperduras</groupId>
    <artifactId>protocoltab</artifactId>
    <version>1.1.3</version>
    <scope>provided</scope>
</dependency>
```

## Example

### Java
```java
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.kacperduras.protocoltab.ProtocolTabAPI;
import pl.kacperduras.protocoltab.manager.ProtocolTab;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class ProtocolTabExample extends JavaPlugin {

    private final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("HH:mm:ss"));

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
            public void onJoin(PlayerJoinEvent event) {
                Player player = event.getPlayer();

                for (int i = 0; i < 80; i++) {
                    ProtocolTabAPI.getTablist(player).setSlot(i, ProtocolTab.BLANK_TEXT);
                }

                ProtocolTabAPI.getTablist(player).setHeader("&cmd_5 is love!");
                ProtocolTabAPI.getTablist(player).setHeader("&6md_5 is life!");

                ProtocolTabAPI.getTablist(player).setSlot(0, "First slot.");
                ProtocolTabAPI.getTablist(player).setSlot(1, "&eSecond slot.");

                ProtocolTabAPI.getTablist(player).update();
                runTask(player);
            }

        }, this);
    }

    private void runTask(Player player) {
        this.getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            ProtocolTabAPI.getTablist(player).setSlot(3, "&a" + dateFormat.get().format(
                    new Date(System.currentTimeMillis())));
            ProtocolTabAPI.getTablist(player).update();
        }, 20L, 20L);
    }

}
```

### Skript
```
set %player%'s header to %string%
set %player%'s footer to %string%
set %player%'s slot number %integer% to %string%
update %player%'s tablist
```

The usage is similar to the Java API.

## Configuration
```yaml
# ProtocolTab basic configuration.

# Default ping on board.
default-ping: '0'
```

## Requirements
* [**ProtocolLib**](https://www.spigotmc.org/resources/protocollib.1997/) for 1.8+ versions;
* Java 8.

## Download
Download is available [**here**](https://github.com/kacperduras/ProtocolTab/releases/).
