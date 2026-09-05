package com.magicchess.larva.game;

import android.content.Context;
import com.magicchess.larva.utils.AssetLoader;
import com.magicchess.larva.utils.SoundManager;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    
    public enum GameState { TITLE, SHOP, BOARD, BATTLE, GAME_OVER }
    
    private Context context;
    private AssetLoader assetLoader;
    private SoundManager soundManager;
    private Player player;
    private Board board;
    private Shop shop;
    private CombatSystem combatSystem;
    private SynergyManager synergyManager;
    private GameState currentState;
    private int currentRound;
    
    public GameManager(Context context) {
        this.context = context;
        this.assetLoader = new AssetLoader(context);
        this.soundManager = new SoundManager(context);
        
        this.player = new Player("Komandan", 100);
        this.board = new Board();
        this.shop = new Shop();
        this.combatSystem = new CombatSystem();
        this.synergyManager = new SynergyManager();
        
        this.currentRound = 1;
        this.currentState = GameState.TITLE;
        
        assetLoader.loadAllAssets();
    }
    
    public void startGame() {
        currentState = GameState.SHOP;
        currentRound = 1;
        player.reset(100);
        board.clear();
        board.setMaxHeroes(player.getLevel());
        shop.refresh(player.getLevel());
    }
    
    public void buyHero(Hero hero) {
        if (player.getGold() >= hero.getCost()) {
            player.spendGold(hero.getCost());
            if (board.addHero(hero)) {
                soundManager.playSound("sound/get_item.ogg");
            } else {
                player.addGold(hero.getCost());
            }
        }
    }
    
    public void sellHero(int boardPosition) {
        Hero hero = board.getHeroAt(boardPosition);
        if (hero != null) {
            player.addGold(hero.getCost() / 2);
            board.removeHero(boardPosition);
        }
    }
    
    public void upgradeHero(int boardPosition) {
        Hero hero = board.getHeroAt(boardPosition);
        if (hero != null && hero.getStarLevel() < 3) {
            List<Hero> duplicates = findDuplicates(hero);
            if (duplicates.size() >= 2) {
                board.removeHero(duplicates.get(0).getPosition());
                board.removeHero(duplicates.get(1).getPosition());
                hero.upgradeStar();
                soundManager.playSound("sound/levelup.ogg");
            }
        }
    }
    
    private List<Hero> findDuplicates(Hero target) {
        List<Hero> duplicates = new ArrayList<>();
        for (Hero h : board.getHeroes()) {
            if (h.getId().equals(target.getId()) && 
                h.getStarLevel() == target.getStarLevel() &&
                h.getPosition() != target.getPosition() &&
                h.getStarLevel() < 3) {
                duplicates.add(h);
            }
        }
        return duplicates;
    }
    
    public void levelUpCommander() {
        int levelCost = player.getLevel() * 4;
        if (player.getGold() >= levelCost) {
            player.spendGold(levelCost);
            player.levelUp();
            board.setMaxHeroes(player.getLevel());
            soundManager.playSound("sound/levelup.ogg");
        }
    }
    
    public void startBattle() {
        currentState = GameState.BATTLE;
        combatSystem.startBattle(this);
        soundManager.playSound("sound/bgm/war_bgm.ogg");
    }
    
    public void processBattleResult(boolean victory) {
        if (victory) {
            player.addGold(2);
            player.incrementWinStreak();
        } else {
            int damage = calculateDamage();
            player.takeDamage(damage);
            player.resetWinStreak();
            
            if (player.getHp() <= 0) {
                currentState = GameState.GAME_OVER;
            }
        }
        
        currentRound++;
        player.addGold(5 + calculateInterest());
        player.incrementRound();
    }
    
    private int calculateDamage() {
        return 2 + player.getLevel() / 2;
    }
    
    private int calculateInterest() {
        return Math.min(6, player.getGold() / 10 * 2);
    }
    
    public void rerollShop() {
        if (player.getGold() >= 2) {
            player.spendGold(2);
            shop.refresh(player.getLevel());
            soundManager.playSound("sound/ui/button.ogg");
        }
    }
    
    // Apply synergy saat hero berubah
    public void refreshSynergies() {
        List<Hero> heroes = board.getHeroes();
        synergyManager.applySynergyBonuses(heroes);
    }
    
    // Getters
    public GameState getCurrentState() { return currentState; }
    public Player getPlayer() { return player; }
    public Board getBoard() { return board; }
    public Shop getShop() { return shop; }
    public CombatSystem getCombatSystem() { return combatSystem; }
    public SynergyManager getSynergyManager() { return synergyManager; }
    public int getCurrentRound() { return currentRound; }
    public SoundManager getSoundManager() { return soundManager; }
    
    public void setCurrentState(GameState state) { this.currentState = state; }
    public void destroy() { soundManager.release(); }
}
