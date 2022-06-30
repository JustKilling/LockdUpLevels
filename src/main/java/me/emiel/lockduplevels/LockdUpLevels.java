package me.emiel.lockduplevels;

import me.emiel.lockduplevels.Commands.LevelingCommand;
import me.emiel.lockduplevels.Storage.DataManager;
import me.emiel.lockduplevels.Storage.PlayerDataListener;
import me.emiel.lockduplevels.Storage.PlayerJsonPersistenceHandler;
import me.emiel.lockduplevels.Storage.PlayerPersistenceHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class LockdUpLevels extends JavaPlugin {

    private static int XP_PER_LEVEL;
    private static double XP_MULTIPLIER_PER_LEVEL;
    private static LockdUpLevels Instance;

    public static LockdUpLevels getInstance() {
        return Instance;
    }

    public static DataManager getPlayerDataManager() {
        return playerDataManager;
    }

    private static DataManager playerDataManager;
    private PlayerPersistenceHandler playerPersistenceHandler;

    @Override
    public void onEnable() {
        Instance = this;
        XP_PER_LEVEL = getConfig().getInt("xp-per-level");
        XP_MULTIPLIER_PER_LEVEL = getConfig().getDouble("multiplier-per-level");
        saveDefaultConfig();

        this.playerPersistenceHandler = new PlayerJsonPersistenceHandler(this);
        playerDataManager = new DataManager(playerPersistenceHandler);
        PlayerDataListener playerDataListener = new PlayerDataListener(playerDataManager);
        Bukkit.getPluginManager().registerEvents(playerDataListener, this);

        //load data for every player online
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerDataManager.loadPlayerData(player.getUniqueId());
        }


        //commands
        getCommand("levels").setExecutor(new LevelingCommand());

        startScheduler();
    }

    @Override
    public void onDisable() {
        playerDataManager.saveAllPlayerData();
    }

    public void startScheduler(){
        new BukkitRunnable() {
            public void run() {
                playerDataManager.saveAllPlayerData();
            }
        }.runTaskTimerAsynchronously(this, 20L * 60 * 5, 20L * 60 * 5); //5minutes
    }


    public static int getXP_PER_LEVEL() {
        return XP_PER_LEVEL;
    }

    public static double getXP_MULTIPLIER_PER_LEVEL() {
        return XP_MULTIPLIER_PER_LEVEL;
    }
}
