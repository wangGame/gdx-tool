package com.read.csv.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kw.gdx.dialog.DialogManager;
import com.read.csv.screen.base.BaseScreen;

public class DemoScreen extends BaseScreen {
    public DemoScreen(){
    }

    @Override
    public void show() {
        super.show();
        Table table = new Table(){{
            Image image = new Image(new Texture("background.png"));
            image.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);

                }
            });
            add();
            pack();
        }};
        stage.addActor(table);
    }
}
