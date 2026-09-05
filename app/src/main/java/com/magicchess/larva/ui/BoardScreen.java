package com.magicchess.larva.ui;

import android.content.Context;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.magicchess.larva.R;
import com.magicchess.larva.game.GameManager;
import com.magicchess.larva.game.Hero;
import java.util.List;

public class BoardScreen extends LinearLayout {
    
    private GameManager gameManager;
    private GridLayout boardGrid;
    private Button btnLevelUp;
    private TextView tvLevelInfo;
    
    public BoardScreen(Context context, GameManager gameManager) {
        super(context);
        this.gameManager = gameManager;
        
        // Inflate layout
        inflate(context, R.layout.board_screen, this);
        
        // Initialize views
        boardGrid = findViewById(R.id.board_grid);
        btnLevelUp = findViewById(R.id.btn_level_up);
        tvLevelInfo = findViewById(R.id.tv_level_info);
        
        // Setup level up button
        btnLevelUp.setOnClickListener(v -> {
            gameManager.levelUpCommander();
            refresh();
        });
        
        refresh();
    }
    
    public void refresh() {
        // Clear grid
        boardGrid.removeAllViews();
        
        // Add hero cells
        List<Hero> heroes = gameManager.getBoard().getHeroes();
        
        // Create 16 cells (4x4)
        for (int i = 0; i < 16; i++) {
            BoardCell cell = new BoardCell(getContext(), gameManager, i);
            boardGrid.addView(cell);
        }
        
        // Update level info
        tvLevelInfo.setText("Level: " + gameManager.getPlayer().getLevel() +
                " | Heroes: " + gameManager.getBoard().getHeroCount() +
                "/" + gameManager.getBoard().getMaxHeroes());
    }
}
