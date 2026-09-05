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
    
    // Base stats untuk reset
    private int baseHp;
    private int baseAttack;
    private int baseDefense;
    private int baseAttackSpeed;
    private int baseMagicPower;
    
    public Hero(String id, String name, String imagePath, int cost) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.cost = cost;
        this.starLevel = 1;
        this.synergies = new ArrayList<>();
        
        // Base stats
        this.hp = 100 + cost * 20;
        this.attack = 10 + cost * 5;
        this.defense = 5 + cost * 2;
        this.attackSpeed = 1;
        this.range = 1;
        this.magicPower = 0;
        this.position = -1;
        
        // Set base stats
        this.baseHp = this.hp;
        this.baseAttack = this.attack;
        this.baseDefense = this.defense;
        this.baseAttackSpeed = this.attackSpeed;
        this.baseMagicPower = this.magicPower;
    }
    
    public void upgradeStar() {
        if (this.starLevel < 3) {
            this.starLevel++;
            this.hp *= 2;
            this.attack *= 2;
            this.defense *= 2;
            this.magicPower *= 2;
            
            // Update base stats juga
            this.baseHp = this.hp;
            this.baseAttack = this.attack;
            this.baseDefense = this.defense;
            this.baseMagicPower = this.magicPower;
        }
    }
    
    // Method untuk apply synergy bonus
    public void applySynergyBonus(String type, int value) {
        switch (type) {
            case "ATTACK_SPEED":
                this.attackSpeed += value;
                break;
            case "ATTACK":
                this.attack += value;
                break;
            case "DEFENSE":
                this.defense += value;
                break;
            case "HP":
                this.hp += value;
                break;
            case "MAGIC":
                this.magicPower += value;
                break;
        }
    }
    
    // Method untuk reset stats ke base
    public void resetStats() {
        this.hp = baseHp;
        this.attack = baseAttack;
        this.defense = baseDefense;
        this.attackSpeed = baseAttackSpeed;
        this.magicPower = baseMagicPower;
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
    
    // Base getters
    public int getBaseHp() { return baseHp; }
    public int getBaseAttack() { return baseAttack; }
    public int getBaseDefense() { return baseDefense; }
    public int getBaseAttackSpeed() { return baseAttackSpeed; }
    public int getBaseMagicPower() { return baseMagicPower; }
    
    // Setters
    public void setStarLevel(int starLevel) { this.starLevel = starLevel; }
    public void setPosition(int position) { this.position = position; }
    public void setSoundPath(String soundPath) { this.soundPath = soundPath; }
    public void setHp(int hp) { this.hp = hp; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setAttackSpeed(int attackSpeed) { this.attackSpeed = attackSpeed; }
    public void setRange(int range) { this.range = range; }
    public void setMagicPower(int magicPower) { this.magicPower = magicPower; }
}
