package com.read.csv.screen;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.kw.gdx.annotation.ScreenResource;
import com.kw.gdx.audio.Asset;
import com.kw.gdx.audio.AudioProcess;
import com.kw.gdx.audio.AudioType;
import com.read.csv.asset.GameAsset;
import com.read.csv.csvdemo.CsvAppDemo;
import com.read.csv.screen.base.BaseScreen;

@ScreenResource("ccs/loading_11.json")
public class LoadingScreen extends BaseScreen {

    @Override
    public void show() {
        super.show();
        //加载资源
        GameAsset asset = new GameAsset();
        asset.loadRes();
        AssetManager assetManager = Asset.getAsset().assetManager;
        assetManager.finishLoading();
        asset.getRes();


        //加载音效
        AudioProcess.prepare(AudioType.class);
        Asset.getAsset().assetManager.finishLoading();
        AudioProcess.loadFinished();

        //播放音效
        AudioProcess.playSound(AudioType.FA_DONG_JI);

        //读取csv
        CsvAppDemo demo = new CsvAppDemo();


        Group bottom = findActor("bottom");
        for (int i = 1; i <= 4; i++) {
            Image baoshi = bottom.findActor("baoshi_"+i);
            baoshi.setVisible(false);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        nextScreen();

    }

    private void nextScreen() {
        setScreen(new DemoScreen());
    }
//
//    private void jietu() {
//        //        Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(0,0,Gdx.graphics.getBackBufferWidth(),Gdx.graphics.getBackBufferHeight());
////        Pixmap convert = convert(pixmap);
////        PixmapIO.writePNG(Gdx.files.absolute("/imageshot/"+System.currentTimeMillis()+".png"),convert);
////        Gdx.app.exit();
//    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }
}
