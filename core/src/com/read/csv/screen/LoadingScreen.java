package com.read.csv.screen;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.kw.gdx.annotation.ScreenResource;
import com.read.csv.screen.base.BaseScreen;

@ScreenResource("ccs/loading_11.json")
public class LoadingScreen extends BaseScreen {

    @Override
    protected void initAnnotation() {
        super.initAnnotation();
    }

    @Override
    public void show() {
        super.show();
        Group bottom = findActor("bottom");
        for (int i = 1; i <= 4; i++) {
            Image baoshi = bottom.findActor("baoshi_"+i);
       //     changeImage(baoshi,"star");
            baoshi.setVisible(false);
        }
    }

    float time = 0;
    @Override
    public void render(float delta) {
        super.render(delta);

    }

    private void nextScreen() {
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