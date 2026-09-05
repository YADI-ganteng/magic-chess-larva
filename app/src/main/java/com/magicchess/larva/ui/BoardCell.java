package com.magicchess.larva.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.magicchess.larva.R;
import com.magicchess.larva.game.GameManager;
import com.magicchess.larva.game.Hero;
import com.squareup.picasso.Picasso;

public class BoardCell extends FrameLayout {
    
    private GameManager gameManager;
    private int position;
    private ImageView imgHero;
    private TextView tvStar;
    private TextView tvName;
    
    public BoardCell(Context context, GameManager gameManager, int position) {
        super(context);
        this.gameManager = gameManager;
        this.position = position;
        
        // Inflate cell layout
        inflate(context, R.layout.board_cell, this);
        
        // Initialize views
        imgHero = findViewById(R.id.img_cell_hero);
        tvStar = findViewById(R.id.tv_cell_star);
        tvName = findViewById(R.id.tv_cell_name);
        
        // Setup click listener
        setOnClickListener(v -> showHeroDetails());
        
        updateDisplay();
    }
    
    private void updateDisplay() {
        Hero hero = gameManager.getBoard().getHeroAt(position);
        
        if (hero != null) {
            imgHero.setVisibility(View.VISIBLE);
            tvStar.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.VISIBLE);
            
            Picasso.get()
                    .load("file:///android_asset/" + hero.getImagePath())
                    .placeholder(R.drawable.placeholder_hero)
                    .error(R.drawable.placeholder_hero)
                    .into(imgHero);
            
            // Show star level
            String stars = "";
            for (int i = 0; i < hero.getStarLevel(); i++) {
                stars += "★";
            }
            tvStar.setText(stars);
            tvName.setText(hero.getName());
        } else {
            imgHero.setVisibility(View.GONE);
            tvStar.setVisibility(View.GONE);
            tvName.setVisibility(View.GONE);
        }
    }
    
    private void showHeroDetails() {
        Hero hero = gameManager.getBoard().getHeroAt(position);
        if (hero != null) {
            // Show dialog with options (sell, upgrade)
            // For now, simple implementation
            // In production, show a proper dialog
        }
    }
    
    public void refresh() {
        updateDisplay();
    }
}
