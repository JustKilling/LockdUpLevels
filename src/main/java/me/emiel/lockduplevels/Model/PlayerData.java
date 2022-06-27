package me.emiel.lockduplevels.Model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PlayerData {
        public UUID uuid;
        public List<Level> levels;
}
