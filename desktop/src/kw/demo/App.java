package kw.demo;

import com.badlogic.gdx.math.Vector2;

public class App {
    public static void main(String[] args) {
        Vector2 vector2 = new Vector2(0,1);
        vector2.setLength(100);
        for (int i = 0; i < 360; i++) {
            vector2.setAngle(i);
        }

//        int levelTemp = 0;
//        StringBuilder showFormat = new StringBuilder();
//        StringBuilder levelPath = new StringBuilder();
//        for (int level = 0; level < 100; level++) {
//            levelTemp = (level) % 8 + 1;
//            int chapter = level / 8 + 1;
//
//
//            showFormat.setLength(0);
//            levelPath.setLength(0);
//
//            showFormat.append(chapter);
//            showFormat.append("-");
//            showFormat.append(levelTemp);
//
//            levelPath.append("level"+chapter+"-");
//            levelPath.append(levelTemp);
//            levelPath.append(".tmx");
//
//            System.out.println(showFormat.toString() + " ---------------------- "+levelPath.toString());
//        }
    }
}
