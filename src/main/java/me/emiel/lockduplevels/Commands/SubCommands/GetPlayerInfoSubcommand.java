package me.emiel.lockduplevels.Commands.SubCommands;

import me.emiel.lockduplevels.Helper.MessageSender;
import me.emiel.lockduplevels.LevelManager;
import me.emiel.lockduplevels.LockdUpLevels;
import me.emiel.lockduplevels.Model.PlayerData;
import me.emiel.lockduplevels.Model.SkillType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class GetPlayerInfoSubcommand extends SubCommand {
    private final String playerName;

    public GetPlayerInfoSubcommand(CommandSender sender, String permission, String playerName){
        super(sender, permission);
        this.playerName = playerName;
    }

    public void execute() {

        if(!checkPermission()) return;

        //check if player exists
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
        if(!player.hasPlayedBefore()){
            MessageSender.SendErrorWithPrefix(sender, "This player was not found!");
            return;
        }
        PlayerData playerData = LockdUpLevels.getPlayerDataManager().getPlayerData(player.getUniqueId());
        var mapData = playerData.getExperienceMap();
        mapData.forEach((key, value) -> {
            MessageSender.SendMessage(sender, "---------------" +"&b&l "+ key.getDisplayName() +" &r" + "---------------");
            MessageSender.SendMessage(sender, "- Level: &b" + value.getLevel());
            MessageSender.SendMessage(sender, "- Xp: &b" + value.getCurrentXp());
            MessageSender.SendMessage(sender, "- Exp needed for next levelup: &b" + (value.calculateTotalExpNeeded(value.getLevel())-value.getCurrentXp()));

        });
    }
}
