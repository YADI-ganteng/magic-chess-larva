package com.magicchess.larva;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.magicchess.larva.game.GameManager;
import com.magicchess.larva.ui.GameScreen;

public class MainActivity extends AppCompatActivity {
    
    private GameManager gameManager;
    private GameScreen gameScreen;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        // Initialize game
        gameManager = new GameManager(this);
        gameScreen = new GameScreen(this, gameManager);
        
        setContentView(gameScreen);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        gameManager.resume();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        gameManager.pause();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameManager.destroy();
    }
}
