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
        
        // Reset semua hero ke base stats dulu
        for (Hero hero : heroes) {
            hero.resetStats();
        }
        
        // Apply bonuses berdasarkan sinergi aktif
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
                if (count >= 6) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Fighter")) {
                            hero.applySynergyBonus("ATTACK", 20);
                            hero.applySynergyBonus("DEFENSE", 12);
                            hero.applySynergyBonus("HP", 200);
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
                            hero.applySynergyBonus("ATTACK", 5);
                        }
                    }
                }
                if (count >= 6) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Mage")) {
                            hero.applySynergyBonus("MAGIC", 60);
                            hero.applySynergyBonus("ATTACK", 10);
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
                if (count >= 6) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Assassin")) {
                            hero.applySynergyBonus("ATTACK_SPEED", 2);
                            hero.applySynergyBonus("ATTACK", 20);
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
                if (count >= 6) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Tank")) {
                            hero.applySynergyBonus("HP", 400);
                            hero.applySynergyBonus("DEFENSE", 20);
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
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Yellow")) {
                            hero.applySynergyBonus("ATTACK_SPEED", 2);
                            hero.applySynergyBonus("ATTACK", 5);
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
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Red")) {
                            hero.applySynergyBonus("ATTACK", 10);
                            hero.applySynergyBonus("DEFENSE", 5);
                        }
                    }
                }
                if (count >= 6) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Red")) {
                            hero.applySynergyBonus("ATTACK", 20);
                            hero.applySynergyBonus("DEFENSE", 10);
                            hero.applySynergyBonus("HP", 100);
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
                if (count >= 5) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Insect")) {
                            hero.applySynergyBonus("ATTACK_SPEED", 2);
                            hero.applySynergyBonus("HP", 100);
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
                if (count >= 3) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Legendary")) {
                            hero.applySynergyBonus("HP", 300);
                            hero.applySynergyBonus("ATTACK", 30);
                            hero.applySynergyBonus("DEFENSE", 15);
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
                if (count >= 6) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Marksman")) {
                            hero.applySynergyBonus("ATTACK_SPEED", 2);
                            hero.applySynergyBonus("ATTACK", 25);
                        }
                    }
                }
                break;
                
            case "Support":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Support")) {
                            hero.applySynergyBonus("HP", 100);
                            hero.applySynergyBonus("MAGIC", 10);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Support")) {
                            hero.applySynergyBonus("HP", 200);
                            hero.applySynergyBonus("MAGIC", 20);
                        }
                    }
                }
                break;
                
            case "Swordsman":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Swordsman")) {
                            hero.applySynergyBonus("ATTACK", 8);
                            hero.applySynergyBonus("ATTACK_SPEED", 1);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Swordsman")) {
                            hero.applySynergyBonus("ATTACK", 15);
                            hero.applySynergyBonus("ATTACK_SPEED", 2);
                        }
                    }
                }
                break;
                
            case "Martial":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Martial")) {
                            hero.applySynergyBonus("ATTACK", 5);
                            hero.applySynergyBonus("DEFENSE", 5);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Martial")) {
                            hero.applySynergyBonus("ATTACK", 10);
                            hero.applySynergyBonus("DEFENSE", 10);
                            hero.applySynergyBonus("HP", 100);
                        }
                    }
                }
                break;
                
            case "Knight":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Knight")) {
                            hero.applySynergyBonus("DEFENSE", 10);
                            hero.applySynergyBonus("HP", 150);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Knight")) {
                            hero.applySynergyBonus("DEFENSE", 20);
                            hero.applySynergyBonus("HP", 300);
                        }
                    }
                }
                break;
                
            case "Mechanic":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Mechanic")) {
                            hero.applySynergyBonus("DEFENSE", 8);
                            hero.applySynergyBonus("ATTACK", 5);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Mechanic")) {
                            hero.applySynergyBonus("DEFENSE", 15);
                            hero.applySynergyBonus("ATTACK", 10);
                        }
                    }
                }
                break;
                
            case "Ranged":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Ranged")) {
                            hero.applySynergyBonus("ATTACK", 8);
                            hero.applySynergyBonus("RANGE", 1);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Ranged")) {
                            hero.applySynergyBonus("ATTACK", 15);
                            hero.applySynergyBonus("RANGE", 2);
                        }
                    }
                }
                break;
                
            case "Bomb":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Bomb")) {
                            hero.applySynergyBonus("MAGIC", 15);
                        }
                    }
                }
                if (count >= 4) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Bomb")) {
                            hero.applySynergyBonus("MAGIC", 30);
                            hero.applySynergyBonus("ATTACK", 5);
                        }
                    }
                }
                break;
                
            case "Prince":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Prince")) {
                            hero.applySynergyBonus("HP", 100);
                            hero.applySynergyBonus("ATTACK", 10);
                        }
                    }
                }
                break;
                
            case "Toy":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Toy")) {
                            hero.applySynergyBonus("DEFENSE", 10);
                            hero.applySynergyBonus("ATTACK_SPEED", 1);
                        }
                    }
                }
                break;
                
            case "Beetle":
                if (count >= 2) {
                    for (Hero hero : heroes) {
                        if (hero.getSynergies().contains("Beetle")) {
                            hero.applySynergyBonus("DEFENSE", 15);
                            hero.applySynergyBonus("HP", 150);
                        }
                    }
                }
                break;
        }
    }
    
    // Get synergy description untuk UI
    public String getSynergyDescription(String synergy, int count) {
        switch (synergy) {
            case "Fighter":
                if (count >= 6) return "6 Fighter: +20 ATK, +12 DEF, +200 HP";
                if (count >= 4) return "4 Fighter: +10 ATK, +6 DEF";
                if (count >= 2) return "2 Fighter: +5 ATK, +3 DEF";
                break;
            case "Mage":
                if (count >= 6) return "6 Mage: +60 MAGIC, +10 ATK";
                if (count >= 4) return "4 Mage: +30 MAGIC, +5 ATK";
                if (count >= 2) return "2 Mage: +15 MAGIC";
                break;
            case "Assassin":
                if (count >= 6) return "6 Assassin: +2 ATK SPD, +20 ATK";
                if (count >= 4) return "4 Assassin: +1 ATK SPD, +12 ATK";
                if (count >= 2) return "2 Assassin: +8 ATK";
                break;
            case "Tank":
                if (count >= 6) return "6 Tank: +400 HP, +20 DEF";
                if (count >= 4) return "4 Tank: +200 HP, +10 DEF";
                if (count >= 2) return "2 Tank: +100 HP, +5 DEF";
                break;
            default:
                return synergy + ": " + count;
        }
        return "";
    }
}
