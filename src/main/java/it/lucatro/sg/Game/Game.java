package it.lucatro.sg.Game;

import it.lucatro.sg.SurvivalGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Game {

    private GameStatus status = GameStatus.LOBBY;
    private boolean inGame;
    private int maxPlayers;
    private int minPlayers;
    private int counter = 15;
    private List<Player> usersGame = new ArrayList<>();

    public Game(int maxPlayers, int minPlayers) {
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
    }

    public void start() {

        SurvivalGames.getInstance().getMatch().setStatus(GameStatus.STARTING);

        new BukkitRunnable() {
            @Override
            public void run() {
                counter--;
                if(counter<=10) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "TIME: " + ChatColor.DARK_GREEN + counter);
                    for(Player player : SurvivalGames.getInstance().getMatch().getPlayers()) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 0.533F);
                        player.sendTitle(ChatColor.DARK_GREEN + "" + counter, "", 1, 20, 1);
                    }
                }
                if(counter == 0) {
                    SurvivalGames.getInstance().getMatch().setStatus(GameStatus.IN_GAME);
                    Bukkit.broadcastMessage("Good game!");
                    for(Player player : SurvivalGames.getInstance().getMatch().getPlayers()) {
                        player.sendTitle(ChatColor.GREEN + "Good game!", "", 1, 20, 1);
                    }

                    teleport();
                    cancel();
                }
            }
        }.runTaskTimer(SurvivalGames.getInstance(), 20, 20);

        Bukkit.broadcastMessage("Game is starting with " + SurvivalGames.getInstance().getMatch().getPlayers().toArray().length + " players");

    }

    public void teleport() {
        HashMap<Player, Location> playerLoc = new HashMap<Player, Location>();
        for(Player player : SurvivalGames.getInstance().getMatch().getPlayers()) {
            playerLoc.put(player, player.getLocation().add(0, 20, 0));
            player.teleport(playerLoc.get(player));
        }
    }

    public boolean started() {
        if(this.status == GameStatus.STARTING || this.status == GameStatus.IN_GAME) return true;
        return false;
    }

    public GameStatus getStatus() {
        return status;
    }

    public boolean inGame(Player player) {
        return (SurvivalGames.getInstance().getMatch().getPlayers().contains(player));
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void addPlayer(Player player) {
        usersGame.add(player);
    }

    public void removePlayer(Player player) {
        if(usersGame.contains(player)) {
            usersGame.remove(player);
        } else {
            player.sendMessage("You are not in game");
        }
    }

    public List<Player> getPlayers() {
        return usersGame;
    }

}
