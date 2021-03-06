package com.kw.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuPolygonSpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.kw.gdx.ads.Constant;
import com.kw.gdx.annotation.AnnotationInfo;
import com.kw.gdx.annotation.GameInfo;


public class BaseGame extends Game {
    private Batch batch;
    private ExtendViewport stageViewport;

    @Override
    public void create() {
        initData();
        initInstance();
        initViewport();
        loadingView();
    }

    private void initData() {
        GameInfo info = AnnotationInfo.checkClassAnnotation(this,GameInfo.class);
        if (info!=null) {
            Constant.WIDTH = info.width();
            Constant.HIGHT = info.height();
            Constant.batchType = info.batch();
            Constant.viewportType = info.viewportType();
        }
    }

    protected void loadingView(){}

    private void initInstance(){
        Gdx.input.setCatchBackKey(true);
    }

    private void initViewport() {
        if (Constant.viewportType == 0) {
            stageViewport = new ExtendViewport(Constant.WIDTH, Constant.HIGHT);
        }else {
            stageViewport = new ExtendViewport(Constant.WIDTH, Constant.HIGHT);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
        viewPortResize(width, height);
    }

    private void viewPortResize(int width, int height) {
        stageViewport.update(width,height);
        stageViewport.apply();
        Constant.GAMEWIDTH = stageViewport.getWorldWidth();
        Constant.GAMEHIGHT = stageViewport.getWorldHeight();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4F, 0.4F, 0.4F, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        super.render();
    }

    public ExtendViewport getStageViewport() {
        return stageViewport;
    }

    public Batch getBatch() {
        if (batch==null) {
            if (Constant.batchType == 0) {
                batch = new CpuPolygonSpriteBatch();
            }else {

            }
        }
        if (batch== null){
            batch = new CpuPolygonSpriteBatch();
        }
        return batch;
    }

    @Override
    public void dispose() {
        super.dispose();
        if (batch!=null) {
            batch.dispose();
            batch = null;
        }
        otherDispose();
    }

    public void otherDispose(){

    }
}
