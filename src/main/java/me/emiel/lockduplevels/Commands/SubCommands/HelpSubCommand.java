package me.emiel.lockduplevels.Commands.SubCommands;

import me.emiel.lockduplevels.Helper.MessageSender;
import org.bukkit.command.CommandSender;

public class HelpSubCommand {
    private final CommandSender sender;
    public HelpSubCommand(CommandSender sender){
        this.sender = sender;
    }

    public void execute() {
        MessageSender.SendMessage(sender, "--------- &b&lLeveling&r ---------");
        MessageSender.SendMessage(sender, "&6&l• &r&e/levels addexp <user> <leveltype> <amount> &7- Add a certain amount of exp to a user");
        MessageSender.SendMessage(sender, "&6&l• &r&e/levels setlevel <user> <leveltype> <amount>&7 - Set a certail level of a user");
        MessageSender.SendMessage(sender, "&6&l• &r&e/levels leveltypes&7 - Show all the level types");
        MessageSender.SendMessage(sender, "&6&l• &r&e/levels getplayerinfo <player>&7 - Show player info for player");
        MessageSender.SendMessage(sender, "&6&l• &r&e/levels reset <player>&7 - Reset a players levels");
    }
}
