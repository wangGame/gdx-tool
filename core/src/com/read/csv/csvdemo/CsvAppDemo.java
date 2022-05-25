package com.read.csv.csvdemo;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.annotation.AnnotationInfo;
import com.kw.gdx.annotation.ExecuteMathod;
import com.kw.gdx.csvanddata.ReadCvs;
import com.kw.gdx.csvanddata.RiderBonus;

import java.io.BufferedReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 读取csv
 */
public class CsvAppDemo {
    ReadCvs readCvs = new ReadCvs();
    public static Array<RiderBonus> riderBonusDataArray;
    public static HashMap<Integer,RiderBonus> riderBonusDataHashMap;

    /**
     * 案例
     */
    @ExecuteMathod
    public void readChapter(){
        riderBonusDataArray = new Array<>();
        riderBonusDataHashMap = new HashMap<Integer,RiderBonus>();
        BufferedReader bufferedReader =
                new BufferedReader(Gdx.files.internal("csv/rider_chap.csv").reader());
        readCvs.readMethodMethod(riderBonusDataArray, bufferedReader,RiderBonus.class);
        for (RiderBonus riderChapter : riderBonusDataArray) {
            riderBonusDataHashMap.put(riderChapter.getChap(),riderChapter);
        }
    }


    public CsvAppDemo(){
        Method[] declaredMethods = this.getClass().getDeclaredMethods();
        for (Method declaredField : declaredMethods) {
            ExecuteMathod annotation = AnnotationInfo.checkMethodAnnotation(declaredField, ExecuteMathod.class);
            if (annotation!=null){
                try {
                    declaredField.invoke(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
