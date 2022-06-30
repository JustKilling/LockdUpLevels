package me.emiel.lockduplevels.Model;

import me.emiel.lockduplevels.LockdUpLevels;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.locks.Lock;

public class Level {

    private String name;
    public int getLevel() {
        return level;
    }

    private int level = 1;

    public int getCurrentXp() {
        return currentXp;
    }

    private int currentXp = 0;

    public int calculateTotalExpNeeded(int level){
        return (int) (LockdUpLevels.getXP_PER_LEVEL() * Math.pow(LockdUpLevels.getXP_MULTIPLIER_PER_LEVEL(),level-1));
    }
    public int calculatePredictedLevel(int xp){
        //Bukkit.broadcastMessage(("Math.log10(" + xp + "/" + LockdUpLevels.getXP_PER_LEVEL() + ")"  + "/"  + "Math.log10(" + LockdUpLevels.getXP_MULTIPLIER_PER_LEVEL() + ")"));

        return (int) Math.floor((Math.log10((double) xp / LockdUpLevels.getXP_PER_LEVEL()) / Math.log10(LockdUpLevels.getXP_MULTIPLIER_PER_LEVEL()) + 1));
    }

    public void addXp(int xp){
//        currentXp += xp;
//        int predicted = calculatePredictedLevel(currentXp);
//
////        Bukkit.broadcastMessage("predicted: " + predicted);
////        Bukkit.broadcastMessage("level:" + level);
////        Bukkit.broadcastMessage("CurrentXp:" + currentXp);
////        Bukkit.broadcastMessage("Needed for next level:" + (calculateTotalExpNeeded(level+1)- currentXp) );
//
//        if(predicted > level){
//            int rest = currentXp - calculateTotalExpNeeded(predicted);
//            level = predicted;
//            currentXp = rest;
//
//        }
        currentXp += xp;
        int xpToLevelUp = calculateTotalExpNeeded(level + 1);
        int oldLevel = level;
        boolean isElse = true;
        while(currentXp >= xpToLevelUp){
            isElse = false;

            level++;
            currentXp -= xpToLevelUp;

        }
        if(isElse){

        }
    }


    public void addXp(int xp, SkillType type, Player player) {
        currentXp += xp;
        int xpToLevelUp = calculateTotalExpNeeded(level + 1);
        int oldLevel = level;
        boolean isElse = true;
        while(currentXp > xpToLevelUp){
                isElse = false;

                level++;
                currentXp -= xpToLevelUp;
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 20,1);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        new TextComponent(ChatColor.translateAlternateColorCodes('&', "Level up! | " + oldLevel + " &l--> &r&f &b&l" + level)));
        }
        if(isElse){
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(ChatColor.translateAlternateColorCodes('&', "&a+&f&l" + xp +" &r&f| &r&b[&f"+ currentXp +"/" + calculateTotalExpNeeded(level+1) +"&r&b]")));

        }





    }
}
