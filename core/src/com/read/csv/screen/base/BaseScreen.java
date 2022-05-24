package com.read.csv.screen.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.kw.gdx.ads.BannerView;
import com.kw.gdx.annotation.AnnotationInfo;
import com.kw.gdx.annotation.ScreenResource;
import com.kw.gdx.cocosload.CocosResource;
import com.kw.gdx.dialog.base.BaseDialog;
import com.read.csv.RiderGame;
import com.read.csv.constant.Constant;

public class BaseScreen implements Screen {
    protected Stage stage;
    protected Group rootView;
    protected String viewpath;
    protected float offsetY;
    protected float offsetX;
    protected boolean back;
    private BannerView bannerView;

    public BaseScreen(){
        stage = new Stage(getStageViewport(), getBatch());
        stage.getRoot().setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        offsetY = (Constant.GAMEHIGHT - Constant.STDHIGHT)/2;
        offsetX = (Constant.GAMEWIDTH - Constant.STDWIDTH)/2;
        bannerView = new BannerView();
        bannerView.setPosition(Constant.GAMEWIDTH /2 ,-offsetY,Align.bottom);
        stage.addActor(bannerView);
    }

    protected void initAnnotation(){

    }

    private Batch getBatch() {
        return RiderGame.instence().getBatch();
    }

    private ExtendViewport getStageViewport() {
        return RiderGame.instence().getStageViewport();
    }

    public void touchDisable(){
        Gdx.input.setInputProcessor(null);
    }

    public void touchEnable(){
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        initTouch();
        initRootView();
        initAnnotation();
    }

    private void initRootView() {
        ScreenResource annotation = AnnotationInfo.checkClassAnnotation(this, ScreenResource.class);
        if (annotation!=null){
            viewpath = annotation.value();
            rootView = CocosResource.loadFile(viewpath);
            stage.addActor(rootView);
            rootView.setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT/2, Align.center);
        }
    }

    private void initTouch() {
        stage.addListener(BackInputListener());
        touchEnable();
    }

    private InputListener BackInputListener() {
        return new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if ((keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK)) {
                    back = true;
                }
                return super.keyDown(event, keycode);
            }
        };
    }

    protected BaseDialog back() {
//        BaseDialog back = RiderGame.instence().getDialogManager().back();
        return null;
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
//        banner.toFront();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
//        observer.clear();
//        CocosResource.unLoadFile(viewpath);
    }

    @Override
    public void dispose() {
        stage.dispose();
        if (viewpath!=null) {
//            CocosRes.unLoadFile(viewpath);
        }
    }

    public void addActor(Actor addActor){
        stage.addActor(addActor);
    }

    public void setScreen(BaseScreen screen) {
        RiderGame.instence().setScreen(screen);
    }

    public <T extends Actor> T findActor(String name){
        return rootView.findActor(name);
    }
}

