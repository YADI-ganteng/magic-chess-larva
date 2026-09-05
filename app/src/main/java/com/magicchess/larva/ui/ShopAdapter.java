package com.magicchess.larva.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.magicchess.larva.R;
import com.magicchess.larva.game.GameManager;
import com.magicchess.larva.game.Hero;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    
    private Context context;
    private GameManager gameManager;
    private List<Hero> heroes;
    
    public ShopAdapter(Context context, GameManager gameManager) {
        this.context = context;
        this.gameManager = gameManager;
        this.heroes = new ArrayList<>();
    }
    
    public void updateHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hero_item, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hero hero = heroes.get(position);
        
        holder.tvName.setText(hero.getName());
        holder.tvStats.setText("HP: " + hero.getHp() + " | ATK: " + hero.getAttack() +
                " | DEF: " + hero.getDefense());
        holder.tvSynergies.setText(String.join(", ", hero.getSynergies()));
        holder.tvCost.setText(hero.getCost() + "g");
        
        // Load image
        Picasso.get()
                .load("file:///android_asset/" + hero.getImagePath())
                .placeholder(R.drawable.placeholder_hero)
                .error(R.drawable.placeholder_hero)
                .into(holder.imgHero);
        
        // Buy button
        holder.btnBuy.setOnClickListener(v -> {
            gameManager.buyHero(hero);
            refreshButton(holder, hero);
        });
        
        // Check if can afford
        holder.btnBuy.setEnabled(gameManager.getPlayer().getGold() >= hero.getCost());
    }
    
    private void refreshButton(ViewHolder holder, Hero hero) {
        holder.btnBuy.setEnabled(gameManager.getPlayer().getGold() >= hero.getCost());
    }
    
    @Override
    public int getItemCount() {
        return heroes.size();
    }
    
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHero;
        TextView tvName;
        TextView tvStats;
        TextView tvSynergies;
        TextView tvCost;
        Button btnBuy;
        
        ViewHolder(View itemView) {
            super(itemView);
            imgHero = itemView.findViewById(R.id.img_hero);
            tvName = itemView.findViewById(R.id.tv_hero_name);
            tvStats = itemView.findViewById(R.id.tv_hero_stats);
            tvSynergies = itemView.findViewById(R.id.tv_hero_synergies);
            tvCost = itemView.findViewById(R.id.tv_hero_cost);
            btnBuy = itemView.findViewById(R.id.btn_buy);
        }
    }
}
