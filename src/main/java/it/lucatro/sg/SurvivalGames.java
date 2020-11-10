package it.lucatro.sg;

import it.lucatro.sg.Commands.CommandSet;
import it.lucatro.sg.Commands.CommandStart;
import it.lucatro.sg.Commands.CommandStatus;
import it.lucatro.sg.Events.Events;
import it.lucatro.sg.Game.Game;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SurvivalGames extends JavaPlugin {

    public static SurvivalGames instance;
    private Game match;

    private File customConfigFile;
    private FileConfiguration customConfig;

    @Override
    public void onEnable() {
        match = new Game(12, 1);
        instance = this;

        createCConfig();

        this.getCommand("startGame").setExecutor(new CommandStart());
        this.getCommand("getStatus").setExecutor(new CommandStatus());
        this.getCommand("setloc").setExecutor(new CommandSet());

        getServer().getPluginManager().registerEvents(new Events(), this);

    }

    public FileConfiguration getCConfig() {
        return this.customConfig;
    }

    private void createCConfig() {
        customConfigFile = new File(getDataFolder(), "location.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("location.yml", false);
        }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
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
