package com.kw.gdx.ads;

import com.badlogic.gdx.scenes.scene2d.Group;

public class BannerView extends Group {
    public BannerView(){
        this(pxToDp(300),pxToDp(50));
    }

    public BannerView(float bannerWidth, float bannerHight) {

//        Image ads = new Image(Asset.getAsset().getTexture("white.png"));
//        ads.setSize(pxToDp(300),pxToDp(50));
////        ads.setSize(pxToDp(300),pxToDp(75));
//
//        ads.setColor(Color.BLACK);
//        ads.setX(Constant.GAMEWIDTH/2, Align.center);
//        addActor(ads);
//        setSize(ads.getWidth(),ads.getHeight());
//        Configuration.bannerHeight = (int) pxToDp(50);
        PixmapImage image = new PixmapImage((int)bannerWidth,(int)bannerHight);
//        addActor(new Image(new Texture(image.getPixmap())));

        /*
        *
        *         int sw =phoneInfo.getH();  //宽
        int sc =  phoneInfo.getW();  //高
        float chicun = phoneInfo.getCc(); // 尺寸   比如 5寸
        double sqrt = Math.sqrt(sw * sw + sc * sc);
        double v = sqrt / chicun;
        NLog.d("v  %s",v);
        float min = Math.max(Constant.STDWIDTH/sw,Constant.STDHIGHT/sc);
        Constant.vvv = v / 160 * min;*/
    }

    public static float pxToDp(float dp){
        return (float) (dp*Constant.vvv + 0.5F);
    }
}
