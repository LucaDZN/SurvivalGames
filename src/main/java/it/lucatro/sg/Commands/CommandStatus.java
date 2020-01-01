package it.lucatro.sg.Commands;

import it.lucatro.sg.SurvivalGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandStatus implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {

        if(cmd.getName().equalsIgnoreCase("getStatus")) {

            sender.sendMessage("GAME STATUS: " + SurvivalGames.getInstance().getMatch().getStatus().toString());

            return true;
        }
        return false;
    }
}
