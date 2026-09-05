package com.magicchess.larva.ui;

import android.content.Context;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.magicchess.larva.R;
import com.magicchess.larva.game.CombatSystem;
import com.magicchess.larva.game.GameManager;
import android.os.Handler;

public class BattleScreen extends RelativeLayout {
    
    private GameManager gameManager;
    private TextView tvBattleStatus;
    private FrameLayout battleField;
    private Button btnBattleResult;
    private Handler handler;
    private boolean battleEnded;
    
    public BattleScreen(Context context, GameManager gameManager) {
        super(context);
        this.gameManager = gameManager;
        this.handler = new Handler();
        this.battleEnded = false;
        
        // Inflate layout
        inflate(context, R.layout.battle_screen, this);
        
        // Initialize views
        tvBattleStatus = findViewById(R.id.tv_battle_status);
        battleField = findViewById(R.id.battle_field);
        btnBattleResult = findViewById(R.id.btn_battle_result);
        
        // Start battle simulation
        startBattleSimulation();
    }
    
    private void startBattleSimulation() {
        tvBattleStatus.setText("Pertarungan Dimulai!");
        
        // Simulate battle ticks
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!battleEnded) {
                    CombatSystem combat = gameManager.getCombatSystem();
                    boolean ended = combat.simulateNextTick();
                    
                    if (ended) {
                        battleEnded = true;
                        onBattleEnded();
                    } else {
                        // Continue simulation
                        handler.postDelayed(this, 500);
                    }
                }
            }
        }, 500);
    }
    
    private void onBattleEnded() {
        // Determine result
        CombatSystem combat = gameManager.getCombatSystem();
        boolean playerAlive = combat.getPlayerUnits().stream()
                .anyMatch(CombatSystem.CombatUnit::isAlive);
        
        boolean victory = playerAlive;
        
        // Process result
        gameManager.processBattleResult(victory);
        
        // Update UI
        if (victory) {
            tvBattleStatus.setText("VICTORY!");
            btnBattleResult.setText("Lanjut");
        } else {
            tvBattleStatus.setText("DEFEAT!");
            btnBattleResult.setText("Lanjut");
        }
        
        btnBattleResult.setVisibility(VISIBLE);
        btnBattleResult.setOnClickListener(v -> {
            // Return to shop
            gameManager.setCurrentState(GameManager.GameState.SHOP);
            // In a real implementation, navigate back to shop screen
        });
    }
    
    public void refresh() {
        // Refresh if needed
    }
}
