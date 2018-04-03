package com.doudou.cn.valueUtils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jinliang on 18-4-3.
 */

public class ResourceXmlUtils {

    private static final String TAG = ResourceXmlUtils.class.getSimpleName();

    /**
     * @param mContext
     * @param filename
     * @return
     */
    public static String getTemplateValue(Context mContext,String filename){

        InputStream in = null;
        try {
            in = mContext.getAssets().open("hk-value/"+filename);
            int readBytes = 0;
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer buffer = new StringBuffer("");
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
                buffer.append("\n");
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }






}
