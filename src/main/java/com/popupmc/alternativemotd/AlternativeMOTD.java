package com.popupmc.alternativemotd;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AlternativeMOTD extends JavaPlugin {
    @Override
    public void onEnable() {
        Splash.populateSplash();

        // Grab Protocol Manager
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        // Handles MOTD
        manager.addPacketListener(new MOTDPacket(this));

        // Log enabled status
        getLogger().info("AlternativeMOTD is enabled.");
    }

    // Log disabled status
    @Override
    public void onDisable() {
        getLogger().info("AlternativeMOTD is disabled");
    }
}
