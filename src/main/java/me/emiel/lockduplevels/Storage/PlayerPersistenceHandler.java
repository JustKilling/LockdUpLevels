package me.emiel.lockduplevels.Storage;

import me.emiel.lockduplevels.Model.PlayerData;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PlayerPersistenceHandler {
    CompletableFuture<PlayerData> loadDataAsync(UUID playerId);
    void saveDataAsync(UUID playerId, PlayerData playerData);
}
