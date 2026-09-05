package com.magicchess.larva.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop {
    private static final int SHOP_SIZE = 5;
    private List<Hero> availableHeroes;
    private Random random;
    
    private static final String[][] HERO_POOL = {
        {"yellow", "Yellow", "image/larva/yellow/stand/yellow_stand_00.png", "1"},
        {"brown", "Brown", "image/larva/unit/brown/stand/brown_stand_00.png", "1"},
        {"blue", "Blue", "image/larva/unit/blue/stand/blue_stand_00.png", "1"},
        {"black", "Black", "image/larva/unit/black/stand/black_stand_00.png", "1"},
        {"pink", "Pink", "image/larva/unit/pink/stand/pink_stand_00.png", "1"},
        {"violet", "Violet", "image/larva/unit/violet/stand/violet_stand_00.png", "2"},
        {"rainbow", "Rainbow", "image/larva/unit/rainbow/idle/rainbow_stand_00.png", "2"},
        {"bombbug", "Bomb Bug", "image/larva/unit/bombbug/stand/bombbug_stand_00.png", "2"},
        {"baby_beetle", "Baby Beetle", "image/larva/unit/baby_beetle/stand/baby_beetle_stand_00.png", "2"},
        {"prince", "Prince", "image/larva/unit/prince/stand/prince_stand_00.png", "3"},
        {"toycar", "Toy Car", "image/larva/unit/toycar/toy_car_00.png", "3"},
        {"black_knight", "Black Knight", "image/larva/unit/black_knight/stand/black_knight_stand_00.png", "3"},
        {"red_zoro", "Red Zoro", "image/larva/red_zoro/idle/red_zoro_idle_00.png", "4"},
        {"red_viking", "Red Viking", "image/larva/red_viking/idle/red_viking_stand_00.png", "4"},
        {"red_ninja", "Red Ninja", "image/larva/red_ninja/stand/red_ninja_stand_00.png", "4"},
        {"red_kungfu", "Red Kung Fu", "image/larva/red_kungfu/idle/red_kungfu_idle_00.png", "4"},
        {"red_spider", "Red Spider", "image/larva/red_spider/stand/red_spider_stand_00.png", "4"},
        {"yellow_hulk", "Yellow Hulk", "image/larva/yellow_hulk/stand/Hulk_stand_00.png", "5"},
        {"rainbow_warrior", "Rainbow Warrior", "image/larva/rainbow_warrior/stand/rainbow_hero_stand_00.png", "5"},
        {"red_iron", "Red Iron", "image/larva/red_iron/stand/red_Iron_stand_00.png", "5"},
        {"red_terminator", "Red Terminator", "image/larva/red_termi/stand/red_terminator_stand_00.png", "5"},
    };
    
    public Shop() {
        this.availableHeroes = new ArrayList<>();
        this.random = new Random();
    }
    
    public void refresh(int playerLevel) {
        availableHeroes.clear();
        
        for (int i = 0; i < SHOP_SIZE; i++) {
            Hero hero = generateRandomHero(playerLevel);
            availableHeroes.add(hero);
        }
    }
    
    private Hero generateRandomHero(int playerLevel) {
        int maxCost = getMaxCostForLevel(playerLevel);
        
        List<String[]> eligiblePool = new ArrayList<>();
        for (String[] heroData : HERO_POOL) {
            int cost = Integer.parseInt(heroData[3]);
            if (cost <= maxCost) {
                eligiblePool.add(heroData);
            }
        }
        
        String[] selected = eligiblePool.get(random.nextInt(eligiblePool.size()));
        
        Hero hero = new Hero(selected[0], selected[1], selected[2], Integer.parseInt(selected[3]));
        addSynergies(hero);
        hero.setSoundPath(getSoundForHero(selected[0]));
        
        return hero;
    }
    
    private int getMaxCostForLevel(int playerLevel) {
        switch (playerLevel) {
            case 1: case 2: return 1;
            case 3: case 4: return 2;
            case 5: case 6: return 3;
            case 7: case 8: return 4;
            default: return 5;
        }
    }
    
    private void addSynergies(Hero hero) {
        switch (hero.getId()) {
            case "yellow":
                hero.addSynergy("Yellow");
                hero.addSynergy("Insect");
                break;
            case "brown":
                hero.addSynergy("Brown");
                hero.addSynergy("Insect");
                break;
            case "blue":
                hero.addSynergy("Blue");
                hero.addSynergy("Insect");
                break;
            case "black":
                hero.addSynergy("Black");
                hero.addSynergy("Insect");
                break;
            case "pink":
                hero.addSynergy("Pink");
                hero.addSynergy("Support");
                break;
            case "violet":
                hero.addSynergy("Violet");
                hero.addSynergy("Mage");
                break;
            case "rainbow":
                hero.addSynergy("Rainbow");
                hero.addSynergy("Fighter");
                break;
            case "bombbug":
                hero.addSynergy("Bomb");
                hero.addSynergy("Ranged");
                break;
            case "baby_beetle":
                hero.addSynergy("Beetle");
                hero.addSynergy("Tank");
                break;
            case "prince":
                hero.addSynergy("Prince");
                hero.addSynergy("Fighter");
                break;
            case "toycar":
                hero.addSynergy("Toy");
                hero.addSynergy("Mechanic");
                break;
            case "black_knight":
                hero.addSynergy("Knight");
                hero.addSynergy("Tank");
                break;
            case "red_zoro":
                hero.addSynergy("Red");
                hero.addSynergy("Swordsman");
                break;
            case "red_viking":
                hero.addSynergy("Red");
                hero.addSynergy("Fighter");
                break;
            case "red_ninja":
                hero.addSynergy("Red");
                hero.addSynergy("Assassin");
                break;
            case "red_kungfu":
                hero.addSynergy("Red");
                hero.addSynergy("Martial");
                break;
            case "red_spider":
                hero.addSynergy("Red");
                hero.addSynergy("Assassin");
                break;
            case "yellow_hulk":
                hero.addSynergy("Yellow");
                hero.addSynergy("Tank");
                hero.addSynergy("Legendary");
                break;
            case "rainbow_warrior":
                hero.addSynergy("Rainbow");
                hero.addSynergy("Fighter");
                hero.addSynergy("Legendary");
                break;
            case "red_iron":
                hero.addSynergy("Red");
                hero.addSynergy("Tank");
                hero.addSynergy("Legendary");
                break;
            case "red_terminator":
                hero.addSynergy("Red");
                hero.addSynergy("Marksman");
                hero.addSynergy("Legendary");
                break;
        }
    }
    
    private String getSoundForHero(String heroId) {
        switch (heroId) {
            case "yellow": return "sound/yellowlive.ogg";
            case "brown": return "sound/brownattack.ogg";
            case "blue": return "sound/attack.ogg";
            case "black": return "sound/blackattack.ogg";
            case "pink": return "sound/heartheal.ogg";
            case "violet": return "sound/violet.ogg";
            case "rainbow": return "sound/rainbow.ogg";
            case "bombbug": return "sound/bombthrow.ogg";
            case "baby_beetle": return "sound/attack_unit.ogg";
            case "prince": return "sound/unitselect.ogg";
            case "toycar": return "sound/minicar.ogg";
            case "black_knight": return "sound/blackknight.ogg";
            case "red_zoro": return "sound/zorrowait.ogg";
            case "red_viking": return "sound/vikingwait.ogg";
            case "red_ninja": return "sound/ninjawait.ogg";
            case "red_kungfu": return "sound/kungpuskill.ogg";
            case "red_spider": return "sound/spider.ogg";
            case "yellow_hulk": return "sound/yellowlive.ogg";
            case "rainbow_warrior": return "sound/rainbowhunter.ogg";
            case "red_iron": return "sound/ironwait.ogg";
            case "red_terminator": return "sound/terminaterwait.ogg";
            default: return "sound/attack.ogg";
        }
    }
    
    public List<Hero> getAvailableHeroes() {
        return availableHeroes;
    }
    
    public Hero getHeroAt(int index) {
        if (index >= 0 && index < availableHeroes.size()) {
            return availableHeroes.get(index);
        }
        return null;
    }
    
    public void removeHero(int index) {
        if (index >= 0 && index < availableHeroes.size()) {
            availableHeroes.remove(index);
        }
    }
}
