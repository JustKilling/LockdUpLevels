package me.emiel.lockduplevels.Commands.SubCommands;

import me.emiel.lockduplevels.Helper.MessageSender;
import me.emiel.lockduplevels.LockdUpLevels;
import me.emiel.lockduplevels.Model.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class ResetPlayerSubCommand extends SubCommand {
    private final String playerName;

    public ResetPlayerSubCommand(CommandSender sender, String permission, String playerName){
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
        LockdUpLevels.getPlayerDataManager().resetPlayerData(player.getUniqueId());
        MessageSender.SendMessage(sender, "Successfully reset &b" + playerName);
    }
}
