package com.read.csv.screen;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.read.csv.screen.base.BaseScreen;

public class DemoScreen extends BaseScreen {
    private CatmullRomSpline spline;
    public DemoScreen(){
        this.spline = new CatmullRomSpline();
        Vector2[] vector2 = new Vector2[10];
        vector2[0].set(0,0);
        vector2[1].set(2,2);
        vector2[2].set(4,5);
        vector2[3].set(5,6);
        vector2[4].set(6,8);
        vector2[5].set(9,5);
        vector2[6].set(50,3);
        vector2[7].set(33,56);

        spline.set(vector2,true);
//        spline.calculate();

    }
}
