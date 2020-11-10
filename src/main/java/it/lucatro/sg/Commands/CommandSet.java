package it.lucatro.sg.Commands;

import it.lucatro.sg.SurvivalGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSet implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {

        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("setloc")) {

            player.sendMessage(SurvivalGames.getInstance().getCConfig().getString("location"));
            player.sendMessage("Location set");

            player.sendMessage(player.getName() + " PLAYERS");

            return true;
        }
        return false;
    }
}
