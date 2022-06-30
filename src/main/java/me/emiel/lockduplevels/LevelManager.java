package me.emiel.lockduplevels;

import me.emiel.lockduplevels.Model.Level;
import me.emiel.lockduplevels.Model.SkillType;

import java.util.ArrayList;

public class LevelManager {

    public static boolean skillExistsByName(String skill) {
        for (SkillType s :
                SkillType.values()) {
            if(s.getDisplayName().equalsIgnoreCase(skill)) return true;
        }
        return false;
    }
    public static SkillType getSkillTypeByName(String skill) {
        for (SkillType s :
                SkillType.values()) {
            if(s.getDisplayName().equalsIgnoreCase(skill)) return s;
        }
        return null;
    }
}
