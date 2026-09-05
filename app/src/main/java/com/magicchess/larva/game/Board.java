package com.magicchess.larva.game;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int BOARD_SIZE = 16;
    
    private Hero[] boardSlots;
    private int maxHeroes;
    
    public Board() {
        this.boardSlots = new Hero[BOARD_SIZE];
        this.maxHeroes = 1;
    }
    
    public void setMaxHeroes(int level) {
        if (level <= 2) maxHeroes = 1;
        else if (level <= 4) maxHeroes = 2;
        else if (level <= 6) maxHeroes = 3;
        else if (level <= 8) maxHeroes = 4;
        else maxHeroes = 5;
    }
    
    public boolean addHero(Hero hero) {
        int heroCount = getHeroCount();
        if (heroCount < maxHeroes) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (boardSlots[i] == null) {
                    boardSlots[i] = hero;
                    hero.setPosition(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean addHeroAt(Hero hero, int position) {
        if (position >= 0 && position < BOARD_SIZE && boardSlots[position] == null) {
            boardSlots[position] = hero;
            hero.setPosition(position);
            return true;
        }
        return false;
    }
    
    public Hero removeHero(int position) {
        if (position >= 0 && position < BOARD_SIZE) {
            Hero hero = boardSlots[position];
            boardSlots[position] = null;
            if (hero != null) hero.setPosition(-1);
            return hero;
        }
        return null;
    }
    
    public Hero getHeroAt(int position) {
        if (position >= 0 && position < BOARD_SIZE) {
            return boardSlots[position];
        }
        return null;
    }
    
    public List<Hero> getHeroes() {
        List<Hero> heroes = new ArrayList<>();
        for (Hero hero : boardSlots) {
            if (hero != null) {
                heroes.add(hero);
            }
        }
        return heroes;
    }
    
    public int getHeroCount() {
        int count = 0;
        for (Hero hero : boardSlots) {
            if (hero != null) count++;
        }
        return count;
    }
    
    public boolean isFull() {
        return getHeroCount() >= maxHeroes;
    }
    
    public void clear() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            boardSlots[i] = null;
        }
    }
    
    public int getMaxHeroes() { return maxHeroes; }
}
