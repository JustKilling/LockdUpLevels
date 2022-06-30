package me.emiel.lockduplevels.Commands.SubCommands;

import me.emiel.lockduplevels.Helper.MessageSender;
import org.bukkit.command.CommandSender;

public class LeveltypesSubCommand extends SubCommand {
    public LeveltypesSubCommand(CommandSender sender, String permission){
        super(sender, permission);
    }

    public void execute() {
        if(!checkPermission()) return;

        MessageSender.SendMessage(sender, "--------- &b&lLevel types&r ---------");
        MessageSender.SendMessage(sender, "&6&l• &r&e/levels addexp <user> <leveltype> <amount> &7- Add a certain amount of exp to a user");

    }


}
