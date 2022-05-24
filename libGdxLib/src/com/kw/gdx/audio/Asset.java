package com.kw.gdx.audio;

import com.kw.gdx.annotation.AnnotationInfo;
import com.kw.gdx.annotation.FtResource;
import com.kw.gdx.annotation.SpineResource;
import com.kw.gdx.annotation.TextureReginAnnotation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import com.kw.gdx.constant.Configuration;
import com.esotericsoftware.spine.SkeletonData;
import com.kw.gdx.log.NLog;
import com.kw.gdx.src.SkeletonDataLoader;
import com.ui.ManagerUIEditor;
import com.ui.loader.ManagerUILoader;
import com.ui.plist.PlistAtlas;
import com.ui.plist.PlistAtlasLoader;

import java.lang.reflect.Field;

public class Asset implements Disposable {

    private static Asset asset;
    public static AssetManager assetManager;
    private FontResource fontResource;
    private TexureRegionResouce regionResouce;
    private int i=0;

    public Sprite findLevelSprite(String s) {
        return regionResouce.atlas.createSprite(s);
    }

    interface Resouce{
        void load();
        void getResource();
    }

    class SpineLoad extends Asset{
        @SpineResource
        String star = "spine/star.json";
        @SpineResource
        String skeleton =   "spine/skeleton.json";
        @SpineResource
        String propSpeedUp = "spine/propSpeedUp.json";
        @SpineResource
        String perfectLanding = "spine/perfectLanding.json";
        @SpineResource
        String finish_caidai = "spine/2_2_finish_caidai.json";
        @SpineResource
        String propaddSpeed_propSpeedUp = "spine/propaddSpeed/propSpeedUp.json";
        @SpineResource(isSpine = false)
        String carExplode = "com/kw/gdx/effect/carExplode";
        @SpineResource(isSpine = false)
        String coinGain = "com/kw/gdx/effect/coinGain3";
        @SpineResource(isSpine = false)
        String jb_tw = "com/kw/gdx/effect/jb_tw";
        @SpineResource(isSpine = false)
        String speedUp = "com/kw/gdx/effect/speedUp";
        @SpineResource(isSpine = false)
        String xx = "com/kw/gdx/effect/xx";
        @SpineResource(isSpine = false)
        String d = "com/kw/gdx/effect/addspeed/jiasu";
        public SpineLoad() {
            Field[] fields = SpineLoad.this.getClass().getDeclaredFields();
            for (Field field : fields) {
                SpineResource annotation = AnnotationInfo.checkFeildAnnotation(field, SpineResource.class);
                if (annotation!=null){
                    if (annotation.isSpine()) {
                        try {
                            assetManager.load((String)field.get(this), SkeletonData.class);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }else {
                        try {
                            assetManager.load((String)field.get(this), ParticleEffect.class);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    class FontResource implements Resouce{
        //        @FtResource("Norwester-Regular_190_1.fnt")
//        private BitmapFont nR_190_1;
        //        @FtResource("Rationale_216_1.fnt")
//        private BitmapFont Ration_216;
//        @FtResource("Rationale_84_1.fnt")
//        private BitmapFont R_84;
//        @FtResource("Rationale_64_1.fnt")
//        private BitmapFont R_64;
        //        @FtResource("Rationale_64_2.fnt")
//        private BitmapFont R_642;
//        @FtResource("Norwester-Regular_52_1.fnt")
//        private BitmapFont N_52;
//        @FtResource("Rationale_67_1.fnt")
//        private BitmapFont R_67;

//        @FtResource("6px2bus_128_1.fnt")
//        private BitmapFont p_b_128;

        //        ----------------------------------------------------
//        @FtResource("Norwester-Regular_90_1.fnt")
//        private BitmapFont N_90;
//        @FtResource("Rationale_60_1.fnt")
////        private BitmapFont R_60;
//        @FtResource("Norwester-Regular_42_1.fnt")
//        private BitmapFont N_42;
//        @FtResource("Rationale_64_3.fnt")
//        private BitmapFont R_643;
//        @FtResource("Rationale_126_1.fnt")
//        private BitmapFont R_126;
//        @FtResource("Norwester-Regular_45_1.fnt")
//        private BitmapFont N_45;
//        @FtResource("Rationale_180_1.fnt")
//        private BitmapFont N_180;
//        ----------------------------------------------------
        private final String fontBasePath = "font/";

        @FtResource("6px_86_w.fnt")
        public BitmapFont px_86;

        @FtResource("Norwester-Regular_56_1.fnt")
        private BitmapFont nR_56_1;

        @FtResource("Norwester-Regular_39_1.fnt")
        private BitmapFont num_39;

        @FtResource("Norwester-Regular_50_1.fnt")
        private BitmapFont N_50;

        @FtResource("Norwester_50_w.fnt")
        private BitmapFont R_w_50;

        @FtResource("Norwester_50_b.fnt")
        private BitmapFont R_b_50;

        @FtResource("N_B_48.fnt")
        private BitmapFont R_B_48;

        @FtResource("pxbus_h_48.fnt")
        private BitmapFont p_h_48;

        @FtResource("pxbus_w_48.fnt")
        private BitmapFont p_hui_48;

        @FtResource("6px2bus_129_1.fnt")
        private BitmapFont p_o_129;

        @FtResource("Norwester_90_w.fnt")
        private BitmapFont n_w_90;

        @FtResource("Norwester-Regular_48_1.fnt")
        private BitmapFont N_W_48;

        @FtResource("6px_48_w.fnt")
        private BitmapFont px_48_w;

        @FtResource("N-R_48_1.fnt")
        private BitmapFont N_R_48_1 ;



        @FtResource("N-R_W_48_1.fnt")
        private BitmapFont N_R_W_48_1 ;


        @FtResource("N-R_B_60_1.fnt")
        private BitmapFont N_R_B_60_1;


        @FtResource("N-R_W_60_1.fnt")
        private BitmapFont N_R_W_60_1;


        @FtResource("H_62_48_1.fnt")
        private BitmapFont H_62_48_1 ;

        @FtResource("W_62_48_1.fnt")
        private BitmapFont W_62_48_1 ;

        @FtResource("N-R_72_1.fnt")
        private BitmapFont N_R_72_1 ;

        @FtResource("6px2bus_48_1.fnt")
        private BitmapFont px2bus_48_1 ;

        @FtResource("N-R_90_1.fnt")
        private BitmapFont N_R_90_1;

        @FtResource("6px2bus_60_1.fnt")
        private BitmapFont px2bus_60_1 ;

        @FtResource("X_62_129_1.fnt")
        private BitmapFont X_62_129_1 ;

        public void load(){
            Field[] fields = FontResource.this.getClass().getDeclaredFields();
            for (Field field : fields) {
                FtResource annotation = AnnotationInfo.checkFeildAnnotation(field, FtResource.class);
                if (annotation!=null){
                    try {
                        BitmapFontLoader.BitmapFontParameter parameter = null;
                        parameter = new BitmapFontLoader.BitmapFontParameter();
                        parameter.genMipMaps = true;
                        parameter.minFilter = Texture.TextureFilter.MipMapLinearLinear;
                        parameter.magFilter = Texture.TextureFilter.Linear;
                        assetManager.load(fontBasePath+annotation.value(), BitmapFont.class,parameter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void getResource(){
            Field[] fields = FontResource.this.getClass().getDeclaredFields();
            for (Field field : fields) {
                FtResource annotation = AnnotationInfo.checkFeildAnnotation(field, FtResource.class);
                if (annotation!=null){
                    try {
                        field.set(FontResource.this,assetManager.get(fontBasePath+annotation.value(), BitmapFont.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class TexureRegionResouce implements Resouce{
        @TextureReginAnnotation(value = "image1/all.atlas")
        private TextureAtlas atlas;
        @Override
        public void load() {
            Field[] fields = TexureRegionResouce.this.getClass().getDeclaredFields();
            TextureAtlasLoader.TextureAtlasParameter parameter = null;

            for (Field field : fields) {
                TextureReginAnnotation annotation = AnnotationInfo.checkFeildAnnotation(field, TextureReginAnnotation.class);
                if (annotation!=null){
                    assetManager.load(annotation.value(), TextureAtlas.class,parameter);
                }
            }
        }
        @Override
        public void getResource() {
            Field[] fields = TexureRegionResouce.this.getClass().getDeclaredFields();
            for (Field field : fields) {
                TextureReginAnnotation annotation = AnnotationInfo.checkFeildAnnotation(field, TextureReginAnnotation.class);
                if (annotation!=null){
                    try {
                        field.set(this,assetManager.get(annotation.value(), TextureAtlas.class));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public TextureAtlas getAtlas(String path){
        assetManager.load(path,TextureAtlas.class);
        assetManager.finishLoading();
        return assetManager.get(path,TextureAtlas.class);
    }

    public TextureAtlas.AtlasRegion findLevelResouce(String name){
        return regionResouce.atlas.findRegion(name);
    }

    public BitmapFont getnR_56_1() {
        return fontResource.nR_56_1;
    }

    public BitmapFont getPx_86(){
        return fontResource.px_86;
    }

    public BitmapFont getNum_39() {
        return fontResource.num_39;
    }

    public BitmapFont getN_50(){
        return fontResource.N_50;
    }

    public BitmapFont getR_w_50(){return fontResource.R_w_50;}

    public BitmapFont getR_B_48(){return fontResource.R_B_48;}



    ///////////////////////////////////////////////////////////////////////////
    public BitmapFont getN_R_48_1(){return fontResource.N_R_48_1;}

    public BitmapFont getN_R_W_48_1(){return fontResource.N_R_W_48_1;}

    public BitmapFont getN_R_B_60_1(){return fontResource.N_R_B_60_1;}

    public BitmapFont getN_R_W_60_1(){return fontResource.N_R_W_60_1;}

    public BitmapFont getH_62_48_1(){return fontResource.H_62_48_1;}

    public BitmapFont getW_62_48_1(){return fontResource.W_62_48_1;}

    public BitmapFont getN_R_72_1(){return fontResource.N_R_72_1;}

    public BitmapFont get6px2bus_48_1(){return fontResource.px2bus_48_1;}

    public BitmapFont getN_R_90_1(){return fontResource.N_R_90_1;}

    public BitmapFont getpx2bus_60_1(){return fontResource.px2bus_60_1;}

    public BitmapFont getX_62_129_1() {
        return fontResource.X_62_129_1;
    };


    public BitmapFont getP_H_48(){
        return fontResource.p_h_48;
    }

    public BitmapFont getP_HUI_48(){
        return fontResource.p_hui_48;
    }

    public BitmapFont getP_O_129(){
        return fontResource.p_o_129;
    }

    public BitmapFont getN_W_90(){
        return fontResource.n_w_90;
    }


    public BitmapFont getN_W_48(){
        return fontResource.N_W_48;
    }

    public BitmapFont getPx_48_w(){
        return fontResource.px_48_w;
    }
    public Texture getTexture(String path){
//        NLog.i(path+" get texture ");
        if (!Gdx.files.internal(path).exists()){
            NLog.e("%s resouce not exist",path);
            return null;
        }
        if (!assetManager.isLoaded(path)) {
            TextureLoader.TextureParameter parameter = new TextureLoader.TextureParameter();
            parameter.magFilter = Texture.TextureFilter.Linear;
            parameter.minFilter = Texture.TextureFilter.Linear;
            assetManager.load(path, Texture.class,parameter);
            assetManager.finishLoading();
        }
        Texture texture = assetManager.get(path, Texture.class);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return assetManager.get(path,Texture.class);
    }

    public Sprite getSprite(String path){
//        NLog.i(path+" get texture ");
        if (!Gdx.files.internal(path).exists()){
            NLog.e("%s resouce not exist",path);
            return null;
        }
        if (!assetManager.isLoaded(path)) {
            TextureLoader.TextureParameter parameter = new TextureLoader.TextureParameter();
            parameter.magFilter = Texture.TextureFilter.Linear;
            parameter.minFilter = Texture.TextureFilter.Linear;
            assetManager.load(path, Texture.class,parameter);
            assetManager.finishLoading();
        }
        Texture texture = assetManager.get(path, Texture.class);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Sprite sprite = new Sprite(texture);
        return sprite;
    }

    public void disposeTexture(String path){
        if (assetManager.isLoaded(path)) {
            NLog.i("%s dispose ",path);
            assetManager.unload(path);
        }
    }

    private Asset(){
        i++;
        if (i>=2){
            throw new RuntimeException("gun");
        }
        assetManager = getAssetManager();
    }

    public AssetManager getAssetManager(){
        if (assetManager == null){
            assetManager = new AssetManager();
            assetManager.setLoader(TiledMap.class,new TmxMapLoader());
            assetManager.setLoader(ManagerUIEditor.class,new ManagerUILoader(assetManager.getFileHandleResolver()));
            assetManager.setLoader(PlistAtlas.class, new PlistAtlasLoader(assetManager.getFileHandleResolver()));
            assetManager.setLoader(SkeletonData.class,new SkeletonDataLoader(assetManager.getFileHandleResolver()));
//            assetManager.setLoader(TextureAtlas.class,new MiniTextureAtlasLoader(assetManager.getFileHandleResolver(), Configuration.scale));
//            assetManager.setLoader(Texture.class,new MiniTextureLoader(assetManager.getFileHandleResolver(),Configuration.scale));
        }
        return assetManager;
    }

    public void loadResource(){
        fontResource = new FontResource();
        fontResource.load();
        regionResouce = new TexureRegionResouce();
        regionResouce.load();
    }

    public void getResouce(){
        fontResource.getResource();
        regionResouce.getResource();
    }

    public static Asset getAsset() {
        if (asset==null){
            asset = new Asset();
        }
        return asset;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        assetManager = null;
        asset = null;
    }

    public void load(){
        loadResource();
//        new CsvAppDemo();
//        new CarColor();
        new SpineLoad();
//        AudioProcess.prepare(assetManager);
    }
}
