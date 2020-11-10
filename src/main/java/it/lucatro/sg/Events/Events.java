package it.lucatro.sg.Events;

import it.lucatro.sg.Game.GameStatus;
import it.lucatro.sg.SurvivalGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;

public class Events implements Listener {

    ScoreboardManager m = Bukkit.getScoreboardManager();
    Scoreboard board = m.getNewScoreboard();

    Objective o = board.registerNewObjective("Gold", "dummy", ChatColor.RED + "" + ChatColor.BOLD + "SURVIVAL GAMES");

    Score score1 = o.getScore(ChatColor.GRAY + "Players");

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        event.setJoinMessage("");

        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        score1.setScore(SurvivalGames.getInstance().getMatch().getPlayers().toArray().length);

        player.setScoreboard(board);

        if(SurvivalGames.getInstance().getMatch().getStatus().equals(GameStatus.IN_GAME)) {
            player.kickPlayer(ChatColor.DARK_RED + "The game is already started");
        }

        if(SurvivalGames.getInstance().getMatch().getPlayers().toArray().length>=SurvivalGames.getInstance().getMatch().getMaxPlayers()) {
            player.kickPlayer(ChatColor.DARK_RED + "Max players reached");
        }

        SurvivalGames.getInstance().getMatch().addPlayer(event.getPlayer());

        player.sendMessage(ChatColor.GREEN + "You have joined the game, waiting for " + (SurvivalGames.getInstance().getMatch().getMinPlayers()-SurvivalGames.getInstance().getMatch().getPlayers().toArray().length) + " other players");
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        event.setQuitMessage("");

        if(SurvivalGames.getInstance().getMatch().inGame(player)) {
            SurvivalGames.getInstance().getMatch().getPlayers().remove(player);
        }
    }

    @EventHandler
    public void onDead(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(SurvivalGames.getInstance().getMatch().getStatus().equals(GameStatus.IN_GAME)) {
            if (SurvivalGames.getInstance().getMatch().inGame(player)) {
                player.kickPlayer(ChatColor.RED + "You are dead!");
            }
        }
    }

}
