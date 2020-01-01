package it.lucatro.sg;

import it.lucatro.sg.Commands.CommandStart;
import it.lucatro.sg.Commands.CommandStatus;
import it.lucatro.sg.Events.Events;
import it.lucatro.sg.Game.Game;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalGames extends JavaPlugin {

    public static SurvivalGames instance;
    private Game match;

    @Override
    public void onEnable() {
        match = new Game(12, 2);
        instance = this;

        if(this.getMatch().getPlayers().toArray().length == 2) {
            this.getMatch().start();
        }

        this.getCommand("startGame").setExecutor(new CommandStart());
        this.getCommand("getStatus").setExecutor(new CommandStatus());
        getServer().getPluginManager().registerEvents(new Events(), this);
        
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Game getMatch() { return match; }

    public static SurvivalGames getInstance() {
        return instance;
    }
}
