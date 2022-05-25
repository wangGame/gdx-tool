package com.read.csv.dialog;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kw.gdx.dialog.base.BaseDialog;

public class ExitDialog extends BaseDialog {

    public ExitDialog() {
        super("ccs/quit.json");
        show();
    }

    @Override
    public void show() {
        super.show();
//        screen.touchDisable();
//        addAction(Actions.delay(0.5F,Actions.run(()->{
//            screen.touchEnable();
//        })));
        Actor yes = findActor("yes");
        yes.setTouchable(Touchable.enabled);
//        yes.addListener(new OrdinaryButtonListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//                yes.setTouchable(Touchable.disabled);
//                Gdx.app.exit();
//            }
//        });


        Actor no = findActor("no");
        no.setTouchable(Touchable.enabled);
//        no.addListener(new OrdinaryButtonListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//                RiderGame.instence().getDialogManager().closeDialog(ExitDialog.this);
//            }
//        });
    }
}
