package com.popupmc.alternativemotd;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import org.bukkit.ChatColor;

public class MOTDPacket extends PacketAdapter {
    public MOTDPacket(AlternativeMOTD plugin) {
        super(plugin, PacketType.Status.Server.SERVER_INFO);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        WrappedServerPing ping = packet.getServerPings().read(0);

        // Grab a random splash message
        String splash = Splash.randomSplash();

        // Build and assemble the MOTD
        // Replace MOTD in config
        ping.setMotD(buildLine1(splash) + "\n" + buildLine2());
    }

    public String buildLine1(String splash) {
        // Color it correctly
        if(splash.contains(ChatColor.COLOR_CHAR + ""))
            splash = ChatColor.AQUA + splash;
        else
            splash = alternateColors(ChatColor.AQUA, ChatColor.DARK_AQUA, splash);

        // Wrap with magic "⬡"
        splash = ChatColor.GRAY + "" + ChatColor.MAGIC + "⬡" + splash;
        splash = splash + ChatColor.GRAY + "" + ChatColor.MAGIC + "⬡";

        // Pre-formatted server name
        String serverName = "&dP&5o&dp&5u&dp&eMC";

        // Build the line
        return ChatColor.translateAlternateColorCodes('&', "&e┇ " + serverName + " " + splash);
    }

    // Pre-Formatted and built
    public String buildLine2() {
        return ChatColor.translateAlternateColorCodes('&', "&r&e┇ &fWelcome &6⬡&cm&4c&cM&4M&cO&6⬡ ⬡&4S&cM&4P&6⬡ ⬡&cE&4c&co&4n&co&4m&cy&6⬡");
    }

    public String alternateColors(ChatColor color1, ChatColor color2, String text) {

        StringBuilder ret = new StringBuilder();

        boolean useColor1 = true;
        for(int i = 0; i < text.length(); i++) {
            char el = text.charAt(i);

            if(useColor1)
                ret.append(color1).append(el);
            else
                ret.append(color2).append(el);

            if(el != ' ')
                useColor1 = !useColor1;
        }

        return ret.toString();
    }
}
