package com.magicchess.larva.utils;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetLoader {
    
    private Context context;
    
    public AssetLoader(Context context) {
        this.context = context;
    }
    
    public void loadAllAssets() {
        loadFileIds();
        loadGameConfig();
    }
    
    private void loadFileIds() {
        try {
            InputStream is = context.getAssets().open("file_ids.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            is.close();
            
            // Store file IDs in memory
            // In a real implementation, you'd parse this JSON and store the mapping
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadGameConfig() {
        try {
            InputStream is = context.getAssets().open("data/gameConfig.xml");
            // Parse config
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String loadTextAsset(String path) {
        try {
            InputStream is = context.getAssets().open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
