package com.magicchess.larva.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatSystem {
    
    public class CombatUnit {
        private Hero hero;
        private int currentHp;
        private int position;
        private int side;
        private boolean alive;
        private float mana;
        
        public CombatUnit(Hero hero, int position, int side) {
            this.hero = hero;
            this.currentHp = hero.getHp();
            this.position = position;
            this.side = side;
            this.alive = true;
            this.mana = 0;
        }
        
        public void takeDamage(int damage) {
            this.currentHp -= damage;
            if (this.currentHp <= 0) {
                this.alive = false;
                this.currentHp = 0;
            }
        }
        
        public void addMana(float amount) {
            this.mana += amount;
        }
        
        public boolean isReadyForSkill() {
            return this.mana >= 100;
        }
        
        public Hero getHero() { return hero; }
        public int getCurrentHp() { return currentHp; }
        public int getPosition() { return position; }
        public int getSide() { return side; }
        public boolean isAlive() { return alive; }
        public float getMana() { return mana; }
        
        public void setMana(float mana) { this.mana = mana; }
    }
    
    private Random random;
    private List<CombatUnit> playerUnits;
    private List<CombatUnit> enemyUnits;
    private boolean battleInProgress;
    
    public CombatSystem() {
        this.random = new Random();
        this.playerUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.battleInProgress = false;
    }
    
    public void startBattle(GameManager gameManager) {
        battleInProgress = true;
        
        playerUnits.clear();
        enemyUnits.clear();
        
        Board board = gameManager.getBoard();
        for (Hero hero : board.getHeroes()) {
            playerUnits.add(new CombatUnit(hero, hero.getPosition(), 0));
        }
        
        int round = gameManager.getCurrentRound();
        generateEnemyUnits(round);
    }
    
    private void generateEnemyUnits(int round) {
        int enemyCount = Math.min(5, 1 + round / 2);
        
        String[] enemyTypes = {"blue", "black", "brown", "yellow", "violet"};
        
        for (int i = 0; i < enemyCount; i++) {
            String type = enemyTypes[random.nextInt(enemyTypes.length)];
            int cost = Math.max(1, round / 5);
            Hero enemyHero = new Hero(type, type.toUpperCase(), 
                "image/larva/unit/" + type + "/stand/" + type + "_stand_00.png", cost);
            
            enemyHero.setHp(100 + round * 10);
            enemyHero.setAttack(10 + round * 2);
            enemyHero.setDefense(5 + round);
            
            enemyUnits.add(new CombatUnit(enemyHero, i, 1));
        }
    }
    
    public boolean simulateNextTick() {
        if (!battleInProgress) return false;
        
        for (CombatUnit unit : playerUnits) {
            if (unit.isAlive()) {
                unitAttack(unit, enemyUnits);
            }
        }
        
        for (CombatUnit unit : enemyUnits) {
            if (unit.isAlive()) {
                unitAttack(unit, playerUnits);
            }
        }
        
        boolean playerAlive = playerUnits.stream().anyMatch(CombatUnit::isAlive);
        boolean enemyAlive = enemyUnits.stream().anyMatch(CombatUnit::isAlive);
        
        if (!playerAlive || !enemyAlive) {
            battleInProgress = false;
            return true;
        }
        
        return false;
    }
    
    private void unitAttack(CombatUnit attacker, List<CombatUnit> targets) {
        CombatUnit target = findNearestTarget(attacker, targets);
        
        if (target != null) {
            int damage = attacker.getHero().getAttack();
            damage = Math.max(1, damage - target.getHero().getDefense() / 2);
            
            target.takeDamage(damage);
            target.addMana(10);
            attacker.addMana(10);
            
            if (attacker.isReadyForSkill()) {
                useSkill(attacker, targets);
                attacker.setMana(0);
            }
        }
    }
    
    private void useSkill(CombatUnit caster, List<CombatUnit> targets) {
        // Simple skill: AoE damage
        int skillDamage = caster.getHero().getAttack() * 2;
        for (CombatUnit target : targets) {
            if (target.isAlive() && Math.abs(target.getPosition() - caster.getPosition()) <= 2) {
                target.takeDamage(skillDamage);
            }
        }
    }
    
    private CombatUnit findNearestTarget(CombatUnit attacker, List<CombatUnit> targets) {
        CombatUnit nearest = null;
        int nearestDist = Integer.MAX_VALUE;
        
        for (CombatUnit target : targets) {
            if (target.isAlive()) {
                int dist = Math.abs(target.getPosition() - attacker.getPosition());
                if (dist < nearestDist) {
                    nearestDist = dist;
                    nearest = target;
                }
            }
        }
        return nearest;
    }
    
    public boolean isBattleInProgress() { return battleInProgress; }
    public List<CombatUnit> getPlayerUnits() { return playerUnits; }
    public List<CombatUnit> getEnemyUnits() { return enemyUnits; }
}
