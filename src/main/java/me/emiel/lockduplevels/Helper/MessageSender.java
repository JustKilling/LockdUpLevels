package me.emiel.lockduplevels.Helper;

import me.emiel.lockduplevels.LockdUpLevels;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageSender {

    public static void SendMessage(CommandSender sender, String message){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void SendMessageWithPrefix(CommandSender sender, String message){

        String prefix = LockdUpLevels.getInstance().getConfig().getString("prefix");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }
    public static void SendErrorWithPrefix(CommandSender sender, String message){
        String prefix = LockdUpLevels.getInstance().getConfig().getString("prefix");
        String code = "&c";
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix  +  code + message));
    }


    public static void BroadcastMessageWithPrefix(String message) {
        String prefix = LockdUpLevels.getInstance().getConfig().getString("prefix");
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }
}
