package it.lucatro.sg.Commands;

import it.lucatro.sg.SurvivalGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandSet implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
        if(cmd.getName().equalsIgnoreCase("setloc")) {

            sender.sendMessage(SurvivalGames.getInstance().getCConfig().getString("location"));
            sender.sendMessage("Location set");

            return true;
        }
        return false;
    }
}
