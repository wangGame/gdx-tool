package com.read.csv;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuPolygonSpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.esotericsoftware.spine.SkeletonRenderer;
//import com.read.csv.screen.LoadingScreen;
//import com.read.csv.screen.base.BaseScreen;
import com.kw.gdx.BaseGame;
import com.kw.gdx.annotation.GameInfo;
import com.read.csv.constant.Constant;
import com.read.csv.screen.LoadingScreen;

@GameInfo(width = 1080,height = 1920)
public class RiderGame extends BaseGame {
    private static RiderGame riderGame;

    protected void loadingView() {
        Gdx.app.postRunnable(()->{
            setScreen(new LoadingScreen());
        });
    }

    public RiderGame(){
        riderGame = this;
    }

    public static RiderGame instence() {
        if (riderGame ==null){
            System.err.println("error rider is null");
        }
        return riderGame;
    }

    @Override
    public void otherDispose() {
        riderGame = null;
    }
}
