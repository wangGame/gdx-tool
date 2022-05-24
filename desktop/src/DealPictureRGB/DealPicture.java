package DealPictureRGB;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.io.File;

/**
 * Created by Doodle on 2020/7/20.
 */

public class DealPicture extends Game {
    private File file = new File("");
    private static final String rootPath = "desktop" + File.separator + "src";

//    private final String srcPath = rootPath + File.separator + "DealPictureRGB" + File.separator + "OldPicture";
//    private final String desPath = rootPath + File.separator + "DealPictureRGB" + File.separator + "NewPicture";
String desPath = rootPath + File.separator + "DealPictureRGB" + File.separator + "NewPicture";

    @Override
    public void create() {




        for (int i = 0; i < 17; i++) {
            String srcPath = rootPath + File.separator + "DealPictureRGB" + File.separator + "OldPicture"+ File.separator+"cars"+File.separator +i;
            File readPictures = new File(srcPath);
            for (File a : readPictures.listFiles()) {
                if (a.getName().endsWith(".png")) {
                    dealPicture(a,i);
                }
            }
        }
    }

    public void dealPicture(File a,int in) {
        Pixmap pixmap = new Pixmap(new FileHandle(a));
        pixmap.setBlending(Pixmap.Blending.None);

        for (int i = 0; i < pixmap.getWidth(); ++i) {
            for (int j = 0; j < pixmap.getHeight(); ++j) {
                Color color = new Color(pixmap.getPixel(i, j));
                if (color.a == 0) {
                    color.r = 0/255.0F;
                    color.g = 0/255.0F;
                    color.b = 0/255.0F;
                    color.a = 0f;
//                    color.r = 1F;
//                    color.g = 1F;
//                    color.b = 1F;
//                    color.a = 0f;
                }
                pixmap.drawPixel(i, j, Color.rgba8888(color));
            }
        }
        pixmap.setBlending(Pixmap.Blending.None);
        PixmapIO.writePNG(new FileHandle(desPath + File.separator + in+File.separator+a.getName()), pixmap);
        pixmap.dispose();
    }

    public Color searchColor(Pixmap pixmap, int i, int j) {
        Color c = new Color(pixmap.getPixel(i, j + 1));
        if (c.a == 1) {
            return c;
        }
        c = new Color(pixmap.getPixel(i, j - 1));
        if (c.a == 1) {
            return c;
        }
        c = new Color(pixmap.getPixel(i + 1, j));
        if (c.a == 1) {
            return c;
        }
        c = new Color(pixmap.getPixel(i - 1, j));
        if (c.a == 1) {
            return c;
        }
        c = new Color(pixmap.getPixel(i + 1, j + 1));
        if (c.a == 1) {
            return c;
        }
        c = new Color(pixmap.getPixel(i - 1, j + 1));
        if (c.a == 1) {
            return c;
        }
        c = new Color(pixmap.getPixel(i + 1, j - 1));
        if (c.a == 1) {
            return c;
        }
        c = new Color(pixmap.getPixel(i - 1, j - 1));
        if (c.a == 1) {
            return c;
        }
        return Color.WHITE;
    }

    public static void main(String[] strings) {
        new LwjglApplication(new DealPicture());
    }
}
