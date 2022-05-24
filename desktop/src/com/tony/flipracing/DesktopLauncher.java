package com.tony.flipracing;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.read.csv.RiderGame;
import com.kw.gdx.log.NLog;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "tony Rider Game";
        config.x = 600;
        config.y = 100;
        config.width = 360;
        config.height = 640;
        new LwjglApplication(new RiderGame(),config);
    }

//    public static double formatDouble(double d,int nscale){
//        BigDecimal bd = new BigDecimal(d);
//        return bd.setScale(nscale,BigDecimal.ROUND_HALF_UP).doubleValue();
//    }
}