package com.magicchess.larva.ui;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.magicchess.larva.R;
import com.magicchess.larva.game.GameManager;

public class ShopScreen extends LinearLayout {
    
    private GameManager gameManager;
    private RecyclerView recyclerView;
    private ShopAdapter adapter;
    private Button btnReroll;
    
    public ShopScreen(Context context, GameManager gameManager) {
        super(context);
        this.gameManager = gameManager;
        
        // Inflate layout
        inflate(context, R.layout.shop_screen, this);
        
        // Initialize views
        recyclerView = findViewById(R.id.recycler_shop);
        btnReroll = findViewById(R.id.btn_reroll);
        
        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ShopAdapter(context, gameManager);
        recyclerView.setAdapter(adapter);
        
        // Setup reroll button
        btnReroll.setOnClickListener(v -> {
            gameManager.rerollShop();
            refresh();
        });
        
        refresh();
    }
    
    public void refresh() {
        adapter.updateHeroes(gameManager.getShop().getAvailableHeroes());
        adapter.notifyDataSetChanged();
    }
}
