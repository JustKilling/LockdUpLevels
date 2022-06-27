package me.emiel.lockduplevels;

import com.bergerkiller.bukkit.common.Common;
import com.bergerkiller.bukkit.common.PluginBase;
import me.emiel.lockduplevels.Storage.DataManager;
import me.emiel.lockduplevels.Storage.PlayerDataListener;
import me.emiel.lockduplevels.Storage.PlayerJsonPersistenceHandler;
import me.emiel.lockduplevels.Storage.PlayerPersistenceHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public final class LockdUpLevels extends PluginBase {

    private static LockdUpLevels Instance;

    public static LockdUpLevels getInstance() {
        return Instance;
    }

    @Override
    public int getMinimumLibVersion() {
        return Common.VERSION;
    }


    private DataManager playerDataManager;
    private PlayerPersistenceHandler playerPersistenceHandler;

    @Override
    public void enable() {
        this.playerPersistenceHandler = new PlayerJsonPersistenceHandler(this);
        this.playerDataManager = new DataManager(playerPersistenceHandler);
        PlayerDataListener playerDataListener = new PlayerDataListener(playerDataManager);
        Bukkit.getPluginManager().registerEvents(playerDataListener, this);

        startScheduler();
    }

    @Override
    public void disable() {

    }

    @Override
    public boolean command(CommandSender commandSender, String s, String[] strings) {
        return false;
    }

    public void startScheduler(){
        new BukkitRunnable() {
            public void run() {
                playerDataManager.saveAllPlayerData();
            }
        }.runTaskTimerAsynchronously(this, 0L, 200L * 5);
    }


}
