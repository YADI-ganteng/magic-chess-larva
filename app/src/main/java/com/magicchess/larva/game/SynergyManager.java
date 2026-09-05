package com.magicchess.larva.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynergyManager {
    
    public Map<String, Integer> calculateActiveSynergies(List<Hero> heroes) {
        Map<String, Integer> synergyCounts = new HashMap<>();
        
        for (Hero hero : heroes) {
            for (String synergy : hero.getSynergies()) {
                synergyCounts.put(synergy, synergyCounts.getOrDefault(synergy, 0) + 1);
            }
        }
        
        return synergyCounts;
    }
    
    public void applySynergyBonuses(List<Hero> heroes) {
        Map<String, Integer> synergyCounts = calculateActiveSynergies(heroes);
        
        // Clear previous bonuses
        for (Hero hero : heroes) {
            hero.setAttack(hero.getBaseAttack());
            hero.setDefense(hero.getBaseDefense());
            hero.setHp(hero.getBaseHp());
            hero.setAttackSpeed(hero.getBaseAttackSpeed());
        }
        
        // Apply new bonuses
        for (Map.Entry<String, Integer> entry : synergyCounts.entrySet()) {
            String synergy = entry.getKey();
            int count = entry.getValue();
            
            applyBonuses(heroes, synergy, count);
        }
    }
    
    private void applyBonuses(List<Hero> heroes, String synergy, int count) {
        switch (synergy) {
            case "Fighter":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Fighter")) {
                            hero.applySynergyBonus("ATTACK", 5);
                            hero.applySynergyBonus("DEFENSE", 3);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Fighter")) {
                            hero.applySynergyBonus("ATTACK", 10);
                            hero.applySynergyBonus("DEFENSE", 6);
                        }
                    }
                }
                break;
                
            case "Mage":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Mage")) {
                            hero.applySynergyBonus("MAGIC", 15);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Mage")) {
                            hero.applySynergyBonus("MAGIC", 30);
                        }
                    }
                }
                break;
                
            case "Assassin":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Assassin")) {
                            hero.applySynergyBonus("ATTACK", 8);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Assassin")) {
                            hero.applySynergyBonus("ATTACK_SPEED", 1);
                            hero.applySynergyBonus("ATTACK", 12);
                        }
                    }
                }
                break;
                
            case "Tank":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Tank")) {
                            hero.applySynergyBonus("HP", 100);
                            hero.applySynergyBonus("DEFENSE", 5);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Tank")) {
                            hero.applySynergyBonus("HP", 200);
                            hero.applySynergyBonus("DEFENSE", 10);
                        }
                    }
                }
                break;
                
            case "Yellow":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Yellow")) {
                            hero.applySynergyBonus("ATTACK_SPEED", 1);
                        }
                    }
                }
                break;
                
            case "Red":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Red")) {
                            hero.applySynergyBonus("ATTACK", 5);
                        }
                    }
                }
                break;
                
            case "Insect":
                if (count >= 3) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Insect")) {
                            hero.applySynergyBonus("ATTACK_SPEED", 1);
                        }
                    }
                }
                break;
                
            case "Legendary":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Legendary")) {
                            hero.applySynergyBonus("HP", 150);
                            hero.applySynergyBonus("ATTACK", 15);
                        }
                    }
                }
                break;
                
            case "Marksman":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Marksman")) {
                            hero.applySynergyBonus("ATTACK", 10);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Marksman")) {
                            hero.applySynergyBonus("ATTACK_SPEED", 1);
                            hero.applySynergyBonus("ATTACK", 15);
                        }
                    }
                }
                break;
        }
    }
}
