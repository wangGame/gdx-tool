package com.tony.flipracing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;

import java.io.File;

/**
 * 得到星星的计算公式   第一页和第二页
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Math.floor(-0.4F));


        //        String cacheDir = "";
//        System.out.println(String.format(Locale.FRENCH,
//                "Can't create cache dir %s, won't be able to log to a file", cacheDir));

//        System.out.println(1920.0F/1280.0F);


//        Queue<Integer> queue = new ArrayDeque<Integer>(3);
//        for (int i = 0; i < 10; i++) {
//            queue.offer(i);
//            if (queue.size()>2){
//                queue.poll();
//            }
//        }


//        Queue queue = new Queue(2);
//        queue.addLast(1);
//        queue.addLast(2);
//        queue.addLast(3);
//        System.out.println(queue.size);


//        File file = new File("E:\\rider\\rider\\Assets\\asset\\droad");
//        for (String s : file.list()) {
//            File file1 = new File("E:\\rider\\rider\\Assets\\asset\\droad\\d"+s);
//            file.renameTo(file1);
//
//
//
//        }

//        File[] files = file.listFiles();
//        for (File file1 : files) {
//            System.out.println("---"+file1.getName());
//            File file2 = new File("E:\\rider\\d"+file1.getName());
//            file1.renameTo(file2);
//
//        }


//        Random random = new Random();
//        for (int i = 1; i <= 100; i++) {
//            int i1 = random.nextInt(53)+1;
//            System.out.print(i1+"   ");
//            if (i%10==0){
//                System.out.println();
//            }
//        }

//        Interpolation.ExpIn exp10In = Interpolation.exp10In;
//        for (int i= 0; i <= 10; i++) {
//            System.out.println(exp10In.apply(i / 10.0F));
//        }

//        changeName();
//        Queue<Integer> queue = new Queue(5);
//        for (int i = 0; i < 10; i++) {
//            queue.addLast(i);
//            if (queue.size>4){
//                queue.removeFirst();
//            }
//        }
//        for (Integer integer : queue) {
//            System.out.println(integer);
//        }

//        for (int i = 0; i < queue.size(); i++) {
//
//        }
    }

    private static void changeName() {
        FileHandle droad = Gdx.files.internal("droad");
        for (FileHandle handle : droad.list()) {
            File file = handle.file();
            File nf = new File("d"+file.getName());
            file.renameTo(nf);
        }
    }

    public static void secondPage(int x){
        int xx = MathUtils.floor(x/10.0F)*550 + (int)((x % 10.0F)+1) * (x % 10) *5;
    }

    public static void fristPage(int x){
        int v1 = 10;
        if ((x-4)%5==0){
            v1 = 0;
        }
        int value= 0;
        if (x<4)value = 10;
        else value= (MathUtils.floor((x - 4) / 5)*15 + 15) * (MathUtils.floor((x - 4) /5) *3  + 2)+
                (MathUtils.ceil(((x - 4.0F)%5.0F)/2.0F))*(10+MathUtils.floor((x - 4)/5.0F)*30+
                        MathUtils.ceil(((x-4)%5.0F)/2)*10)+v1;
    }

    public static int calAngle(double x1, double y1, double x2, double y2) {
        double x = Math.abs(x1 - x2);
        double y = Math.abs(y1 - y2);
        double z = Math.sqrt(x * x + y * y);
        int angle = Math.round((float) (Math.asin(y / z) / Math.PI * 180));
        if (x2<x1){
            if (y1>y2){
                angle += 180;
            }else {
                angle = 180 - angle;
            }
        }else {
            if (y1>y2){
                angle = 360 - angle;
            }
        }

        return angle;
    }

}
