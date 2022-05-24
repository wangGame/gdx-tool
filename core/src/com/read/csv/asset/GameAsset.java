package com.read.csv.asset;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.annotation.FtResource;
import com.kw.gdx.annotation.SpineResource;
import com.kw.gdx.annotation.TextureReginAnnotation;
import com.kw.gdx.audio.Asset;
import com.kw.gdx.audio.AudioProcess;

public class GameAsset {

    @SpineResource
    public String star = "spine/star.json";
    @SpineResource(isSpine = false)
    public String carExplode = "effect/carExplode";

    @FtResource("font/6px_86_w.fnt")
    public BitmapFont px_86;

    @TextureReginAnnotation(value = "image1/all.atlas")
    public TextureAtlas atlas;


    public void loadRes(){
        Asset.getAsset().loadAsset(GameAsset.this);
    }

    public void getRes(){
        Asset.getAsset().getResource(GameAsset.this);
    }
}
