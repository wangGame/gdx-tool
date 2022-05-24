package com.kw.gdx.ads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class PixmapImage {
    private Texture texture;
    public PixmapImage(int bannerWidth, int bannerHight){
//        pixmap = new Pixmap(bannerWidth,bannerHight,Pixmap.Format.RGBA8888);
//        for (int i = 0; i < bannerWidth; i++) {
//            for (int i1 = 0; i1 < bannerHight; i1++) {
//                p;
//            }
//        }
        Pixmap x2 = new Pixmap(Gdx.files.internal("white.png"));
        Pixmap x = new Pixmap(Gdx.files.internal("fanye.png"));
//        for (int i = 10; i < 10+x2.getWidth(); i++) {
//            for (int i1 = 0; i1 < x2.getHeight(); i1++) {
//                x.drawPixel(x2.getPixel(i-10,));
//            }
//        }

//        ByteBuffer pixels = x2.getPixels();

        x.drawPixmap(x2,10,10);
        texture = new Texture(x);

//        pixmap.setColor(Color.BLACK);
    }

    public Texture getPixmap() {
        return texture;
    }
}
