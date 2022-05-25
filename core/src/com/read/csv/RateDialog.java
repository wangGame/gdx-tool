//package com.read.csv;
//
//
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Touchable;
//import com.badlogic.gdx.scenes.scene2d.actions.Actions;
//import com.badlogic.gdx.utils.Align;
//import com.kw.gdx.dialog.DialogManager;
//import com.kw.gdx.dialog.base.BaseDialog;
//
//public class RateDilog extends BaseDialog {
//
//    public RateDilog() {
//        super("ccs/Rate1.json");
//        type = DialogManager.Type.hideOldShowCurr;
//        show();
//    }
//
//    @Override
//    public void show() {
//        super.show();
//        screen.touchDisable();
//        addAction(Actions.delay(Constant.DialogDelay,Actions.run(()->{
//            screen.touchEnable();
//        })));
//        Actor not_really = findActor("btnNo");
//        not_really.setTouchable(Touchable.enabled);
//        not_really.setOrigin(Align.center);
//        not_really.addListener(new OrdinaryButtonListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//                RiderGame.instence().getDialogManager().showDialog(new RateDilog2());
//            }
//        });
//
//        Actor love_it = findActor("btnYes");
//        love_it.setTouchable(Touchable.enabled);
//        love_it.setOrigin(Align.center);
//        love_it.addListener(new OrdinaryButtonListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
////                if (RiderGame.instence().listener.getSdkInt()>=21) {
////                    RiderGame.instence().listener.newRate();
////                    RiderGame.instence().getDialogManager().closeDialog(RateDilog1.this);
////                }else {
////                }
//                RiderGame.instence().getDialogManager().showDialog(new RateDilog3());
//            }
//        });
//    }
//}
