package me.emiel.lockduplevels.Commands;

import me.emiel.lockduplevels.Commands.SubCommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LevelingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        HelpSubCommand helpCmd = new HelpSubCommand(sender);
        if(args.length == 0){
            helpCmd.execute();
            return true;
        }

        String arg1 = args[0];
        if(arg1.equalsIgnoreCase("leveltypes")){
            SubCommand subCommand = new LeveltypesSubCommand(sender, "leveltypes");
            subCommand.execute();
            return true;
        }else if(args.length == 2){
            if(arg1.equalsIgnoreCase("getplayerinfo")){
                SubCommand subCommand = new GetPlayerInfoSubcommand(sender, "getplayerinfo", args[1]);
                subCommand.execute();
                return true;
            }else if(arg1.equalsIgnoreCase("reset")){
                SubCommand subCommand = new ResetPlayerSubCommand(sender, "reset", args[1]);
                subCommand.execute();
                return true;
            }

        }
        else if(args.length >= 4) {
            if(arg1.equalsIgnoreCase("addxp") ||arg1.equalsIgnoreCase("addexp")){
                SubCommand subCommand = new AddXpSubCommand(sender, "addxp", args[1], args[2], args[3]);
                subCommand.execute();
                return true;
            }else if(arg1.equalsIgnoreCase("setlevel")){
                SubCommand subCommand = new SetLevelSubCommand(sender, "setlevel", args[1], args[2], args[3]);
                subCommand.execute();
                return true;
            }

        }

        helpCmd.execute();

        return true;
    }
}
