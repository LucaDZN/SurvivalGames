package it.lucatro.sg.Commands;

import it.lucatro.sg.SurvivalGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        if(cmd.getName().equalsIgnoreCase("startGame")) {

            SurvivalGames.getInstance().getMatch().start();

            return true;
        }
        return false;
    }
}
