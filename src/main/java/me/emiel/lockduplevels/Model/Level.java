package me.emiel.lockduplevels.Model;

public abstract class Level {
    private final int XP_PER_LEVEL = 25;
    private String name;
    private int level;
    private int currentXp;

    public int calculateTotalExpNeeded(){
        return ((XP_PER_LEVEL / 2) * (level ^ 2)) + ((XP_PER_LEVEL / 2) * level);
    }
    public int calculatePredictedLevel(int totalXp){
        return (int) Math.floor((((double) -XP_PER_LEVEL / 2) + Math.sqrt(((Math.pow(XP_PER_LEVEL, 2))) + ((XP_PER_LEVEL * 2) * totalXp))) / XP_PER_LEVEL);
    }

    public void addXp(int xp){
        int requiredXp = calculateTotalExpNeeded();
        int total = requiredXp + xp;
        int predicted = calculatePredictedLevel(total);
        if(predicted > level){
            level = predicted;
        }
    }

}
