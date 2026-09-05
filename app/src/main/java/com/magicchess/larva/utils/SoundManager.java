package com.magicchess.larva.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    
    private SoundPool soundPool;
    private Map<String, Integer> soundMap;
    private Context context;
    
    public SoundManager(Context context) {
        this.context = context;
        this.soundMap = new HashMap<>();
        this.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    }
    
    public void loadSound(String name, String assetPath) {
        try {
            int soundId = soundPool.load(context.getAssets().openFd(assetPath), 1);
            soundMap.put(name, soundId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playSound(String assetPath) {
        Integer soundId = soundMap.get(assetPath);
        if (soundId != null) {
            soundPool.play(soundId, 1f, 1f, 1, 0, 1f);
        }
    }
    
    public void loadDefaultSounds() {
        // Load common sounds
        loadSound("sound/ui/button.ogg", "sound/ui/button.ogg");
        loadSound("sound/get_item.ogg", "sound/get_item.ogg");
        loadSound("sound/attack.ogg", "sound/attack.ogg");
        loadSound("sound/levelup.ogg", "sound/levelup.ogg");
        loadSound("sound/fall.ogg", "sound/fall.ogg");
    }
    
    public void release() {
        soundPool.release();
    }
}
