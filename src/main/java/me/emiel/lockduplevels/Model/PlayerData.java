package me.emiel.lockduplevels.Model;

import lombok.Data;
import me.emiel.lockduplevels.Helper.MessageSender;
import org.bukkit.entity.Player;

import java.util.*;

@Data
public class PlayerData {
        public Map<SkillType, Level> getExperienceMap() {
                return experienceMap;
        }
        private Map<SkillType, Level> experienceMap = new HashMap<>();

        public void addExp(SkillType type, int exp){
                experienceMap.computeIfAbsent(type, key -> new Level());
                experienceMap.get(type).addXp(exp);
        }
        public void addExp(SkillType type, int exp,Player player){
                experienceMap.computeIfAbsent(type, key -> new Level());
                experienceMap.get(type).addXp(exp, type ,player);
        }
        public int getLevel(SkillType type){
                experienceMap.computeIfAbsent(type, key -> new Level());
                return experienceMap.get(type).getLevel();
        }
        public int getExp(SkillType type){
                experienceMap.computeIfAbsent(type, key -> new Level());
                return experienceMap.get(type).getCurrentXp();
        }
        public void resetAll(){
                var mapData = getExperienceMap();
                mapData.forEach((key, value) -> {
                        value = new Level();
                        mapData.replace(key, value);
                });
                experienceMap = mapData;
        }

}
