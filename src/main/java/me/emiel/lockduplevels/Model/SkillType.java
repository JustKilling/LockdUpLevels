package me.emiel.lockduplevels.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SkillType {
    WOOD_WORKING("Woodworking"),
    CLEANING("Cleaning");

    @Getter
    private final String displayName;
}
