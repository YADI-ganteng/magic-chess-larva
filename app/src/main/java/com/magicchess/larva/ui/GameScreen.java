package com.magicchess.larva.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.magicchess.larva.R;
import com.magicchess.larva.game.GameManager;

public class GameScreen extends FrameLayout {
    
    private GameManager gameManager;
    private TextView tvRound;
    private TextView tvGold;
    private TextView tvHp;
    private Button btnShop;
    private Button btnBoard;
    private Button btnBattle;
    private FrameLayout gameArea;
    
    private ShopScreen shopScreen;
    private BoardScreen boardScreen;
    private BattleScreen battleScreen;
    
    public GameScreen(Context context, GameManager gameManager) {
        super(context);
        this.gameManager = gameManager;
        
        // Inflate layout
        inflate(context, R.layout.game_screen, this);
        
        // Initialize views
        tvRound = findViewById(R.id.tv_round);
        tvGold = findViewById(R.id.tv_gold);
        tvHp = findViewById(R.id.tv_hp);
        btnShop = findViewById(R.id.btn_shop);
        btnBoard = findViewById(R.id.btn_board);
        btnBattle = findViewById(R.id.btn_battle);
        gameArea = findViewById(R.id.game_area);
        
        // Create screens
        shopScreen = new ShopScreen(context, gameManager);
        boardScreen = new BoardScreen(context, gameManager);
        battleScreen = new BattleScreen(context, gameManager);
        
        // Setup buttons
        btnShop.setOnClickListener(v -> showScreen("shop"));
        btnBoard.setOnClickListener(v -> showScreen("board"));
        btnBattle.setOnClickListener(v -> {
            gameManager.startBattle();
            showScreen("battle");
        });
        
        // Show initial screen
        showScreen("shop");
    }
    
    private void showScreen(String screenName) {
        gameArea.removeAllViews();
        
        switch (screenName) {
            case "shop":
                gameArea.addView(shopScreen);
                break;
            case "board":
                gameArea.addView(boardScreen);
                break;
            case "battle":
                gameArea.addView(battleScreen);
                break;
        }
        
        updateTopBar();
    }
    
    private void updateTopBar() {
        tvRound.setText("Ronde: " + gameManager.getCurrentRound());
        tvGold.setText("Gold: " + gameManager.getPlayer().getGold());
        tvHp.setText("HP: " + gameManager.getPlayer().getHp());
    }
    
    public void refreshUI() {
        updateTopBar();
        shopScreen.refresh();
        boardScreen.refresh();
    }
}
