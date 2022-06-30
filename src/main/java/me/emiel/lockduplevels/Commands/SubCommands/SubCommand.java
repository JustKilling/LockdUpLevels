package me.emiel.lockduplevels.Commands.SubCommands;

import me.emiel.lockduplevels.Helper.MessageSender;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
    protected final CommandSender sender;
    protected final String permission;
    public SubCommand(CommandSender sender, String permission){
        this.sender = sender;
        this.permission = permission;
    }

    public abstract void execute() ;

    protected boolean checkPermission() {
        if(!sender.hasPermission(getPermission())){
            MessageSender.SendErrorWithPrefix(sender, "You do not have permission to execute this command!");
            return false;
        }
        return true;
    }

    protected String getPermission() {
        return "leveling." + permission;
    }
    protected static boolean isNumericAndPositive(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);

            return d > 0;

        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
