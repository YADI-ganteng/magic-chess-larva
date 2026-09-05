package com.magicchess.larva.game;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String id;
    private String name;
    private String imagePath;
    private String soundPath;
    private int cost;
    private int starLevel;
    private int hp;
    private int attack;
    private int defense;
    private int attackSpeed;
    private int range;
    private int magicPower;
    private int position;
    private List<String> synergies;
    
    public Hero(String id, String name, String imagePath, int cost) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.cost = cost;
        this.starLevel = 1;
        this.synergies = new ArrayList<>();
        
        this.hp = 100 + cost * 20;
        this.attack = 10 + cost * 5;
        this.defense = 5 + cost * 2;
        this.attackSpeed = 1;
        this.range = 1;
        this.magicPower = 0;
        this.position = -1;
    }
    
    public void upgradeStar() {
        if (this.starLevel < 3) {
            this.starLevel++;
            this.hp *= 2;
            this.attack *= 2;
            this.defense *= 2;
            this.magicPower *= 2;
        }
    }
    
    public void addSynergy(String synergy) {
        if (!synergies.contains(synergy)) {
            synergies.add(synergy);
        }
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getImagePath() { return imagePath; }
    public String getSoundPath() { return soundPath; }
    public int getCost() { return cost; }
    public int getStarLevel() { return starLevel; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getAttackSpeed() { return attackSpeed; }
    public int getRange() { return range; }
    public int getMagicPower() { return magicPower; }
    public int getPosition() { return position; }
    public List<String> getSynergies() { return synergies; }
    
    public void setStarLevel(int starLevel) { this.starLevel = starLevel; }
    public void setPosition(int position) { this.position = position; }
    public void setSoundPath(String soundPath) { this.soundPath = soundPath; }
}
