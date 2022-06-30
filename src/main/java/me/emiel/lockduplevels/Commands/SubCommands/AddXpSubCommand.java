package me.emiel.lockduplevels.Commands.SubCommands;

import me.emiel.lockduplevels.Helper.MessageSender;
import me.emiel.lockduplevels.LevelManager;
import me.emiel.lockduplevels.LockdUpLevels;
import me.emiel.lockduplevels.Model.PlayerData;
import me.emiel.lockduplevels.Model.SkillType;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class AddXpSubCommand extends SubCommand {
    private final String playerName;
    private final String levelType;
    private final String amount;

    public AddXpSubCommand(CommandSender sender, String permission, String playerName, String levelType, String amount){
        super(sender, permission);
        this.playerName = playerName;
        this.levelType = levelType;
        this.amount = amount;
    }

    public void execute() {

        if(!checkPermission()) return;

        //check if player exists
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
        if(!player.hasPlayedBefore()){
            MessageSender.SendErrorWithPrefix(sender, "This player was not found!");
            return;
        }

        if(!LevelManager.skillExistsByName(levelType)){
            MessageSender.SendErrorWithPrefix(sender, "This level type does not exist!");
            return;
        }

        if(!isNumericAndPositive(amount)){
            MessageSender.SendErrorWithPrefix(sender, "Please provide a valid numeric amount!");
            return;
        }

        PlayerData playerData = LockdUpLevels.getPlayerDataManager().getPlayerData(player.getUniqueId());
        SkillType s = LevelManager.getSkillTypeByName(levelType);
        if(!player.isOnline()){
            playerData.addExp(s, Integer.parseInt(amount));
        }else{
            playerData.addExp(s, Integer.parseInt(amount), player.getPlayer());
        }

        MessageSender.SendMessageWithPrefix(sender, "Successfully given user " + player.getName() + " " + amount + " exp for skill: &b" + s.getDisplayName());
    }


}
