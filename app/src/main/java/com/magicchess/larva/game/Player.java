package com.magicchess.larva.game;

public class Player {
    private String name;
    private int hp;
    private int gold;
    private int level;
    private int exp;
    private int winStreak;
    private int loseStreak;
    private int currentRound;
    
    public Player(String name, int maxHp) {
        this.name = name;
        this.hp = maxHp;
        this.gold = 0;
        this.level = 1;
        this.exp = 0;
        this.winStreak = 0;
        this.loseStreak = 0;
        this.currentRound = 0;
    }
    
    public void reset(int maxHp) {
        this.hp = maxHp;
        this.gold = 3;
        this.level = 1;
        this.exp = 0;
        this.winStreak = 0;
        this.loseStreak = 0;
    }
    
    public void addGold(int amount) {
        this.gold += amount;
    }
    
    public void spendGold(int amount) {
        this.gold -= amount;
        if (this.gold < 0) this.gold = 0;
    }
    
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }
    
    public void levelUp() {
        if (this.level < 10) {
            this.level++;
            this.exp = 0;
        }
    }
    
    public void addExp(int amount) {
        this.exp += amount;
        int requiredExp = this.level * 4;
        if (this.exp >= requiredExp && this.level < 10) {
            levelUp();
        }
    }
    
    public void incrementWinStreak() {
        this.winStreak++;
        this.loseStreak = 0;
        if (this.winStreak >= 3) {
            this.gold += 1;
        }
        if (this.winStreak >= 5) {
            this.gold += 1;
        }
    }
    
    public void resetWinStreak() {
        this.winStreak = 0;
        this.loseStreak++;
        if (this.loseStreak >= 3) {
            this.gold += 1;
        }
    }
    
    public void incrementRound() {
        this.currentRound++;
        this.addExp(2);
    }
    
    // Getters and setters
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getGold() { return gold; }
    public int getLevel() { return level; }
    public int getExp() { return exp; }
    public int getWinStreak() { return winStreak; }
    public int getLoseStreak() { return loseStreak; }
    public int getCurrentRound() { return currentRound; }
    
    public void setGold(int gold) { this.gold = gold; }
    public void setLevel(int level) { this.level = level; }
}
