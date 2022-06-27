package me.emiel.lockduplevels.Storage;

import com.bergerkiller.bukkit.common.PluginBase;
import com.bergerkiller.bukkit.common.dep.gson.Gson;
import com.bergerkiller.bukkit.common.dep.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import me.emiel.lockduplevels.Model.PlayerData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
@RequiredArgsConstructor
public class PlayerJsonPersistenceHandler implements PlayerPersistenceHandler {
    private final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private final PluginBase plugin;

    private File getPlayerDataFile(UUID playerId) {
        File folder = plugin.getDataFolder();
        File dataFolder = new File(folder, "playerdata");
        File playerFile = new File(dataFolder, playerId.toString() + ".json");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        return playerFile;
    }

    @Override
    public CompletableFuture<PlayerData> loadDataAsync(UUID playerId) {
        File playerFile = getPlayerDataFile(playerId);
        return CompletableFuture.supplyAsync(() -> {
            if (!playerFile.exists()) {
                return new PlayerData();
            }
            try {
                String json = Files.readString(playerFile.toPath());
                return gson.fromJson(json, PlayerData.class);
            } catch (IOException e) {
                e.printStackTrace();
                return new PlayerData();
            }
        });
    }

    @Override
    public void saveDataAsync(UUID playerId, PlayerData playerData) {
        File playerFile = getPlayerDataFile(playerId);
        String json = gson.toJson(playerData);
        CompletableFuture.runAsync(() -> {
            try {
                Files.writeString(playerFile.toPath(), json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
