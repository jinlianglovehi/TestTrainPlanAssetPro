package com.doudou.cn.utils;

import android.content.Context;

import com.doudou.cn.valueUtils.ResourceXmlUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by jinliang on 16/11/10.
 */

public class SAXUtils {


    /**
     * 通过 xml 文件中的数据获取 解析 trainPlan 集合
     * @param mContext
     * @return
     */
    public static List<TrainPlan> getTrainPlanFromXml(Context mContext,int type){

        try {
            int rawId =RessourceUtils.getFilePathByCategoryType(mContext,type);
            InputStream inputStream = mContext.getResources().openRawResource(rawId);
            SAXParserFactory factory= SAXParserFactory.newInstance();
            //实例化SAX解析器。
            SAXParser sParser=factory.newSAXParser();
            //实例化工具类MyHandler，设置需要解析的节点
            XMLTrainPlanHandler myHandler=new XMLTrainPlanHandler();

            // 开始解析
            sParser.parse(inputStream, myHandler);

            // 解析完成之后，关闭流
            inputStream.close();
            //返回解析结果。
            List<TrainPlan> list = myHandler.getTrainPlans();

            return myHandler.getTrainPlans();  //在这里返回解析之后的数据
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public static List<DayTrainPlan> getDayTrainPlanFromXml(Context mContext,int type){

        try {
            int rawId =RessourceUtils.getFilePathByCategoryType(mContext,type);
            InputStream inputStream = mContext.getResources().openRawResource(rawId);
            SAXParserFactory factory= SAXParserFactory.newInstance();
            //实例化SAX解析器。
            SAXParser sParser=factory.newSAXParser();
            //实例化工具类MyHandler，设置需要解析的节点
            XMLDayTrainPlanHandler myHandler=new XMLDayTrainPlanHandler();

            // 开始解析
            sParser.parse(inputStream, myHandler);

            // 解析完成之后，关闭流
            inputStream.close();
            //返回解析结果。
            List<DayTrainPlan> list = myHandler.getDayTrainPlanList();

            return myHandler.getDayTrainPlanList();  //在这里返回解析之后的数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<RunRemind> getRunRemindFromXml(Context mContext,int type){

        try {
            int rawId =RessourceUtils.getFilePathByCategoryType(mContext,type);
            InputStream inputStream = mContext.getResources().openRawResource(rawId);
            SAXParserFactory factory= SAXParserFactory.newInstance();
            //实例化SAX解析器。
            SAXParser sParser=factory.newSAXParser();
            //实例化工具类MyHandler，设置需要解析的节点
            XMLRunRemindHandler myHandler=new XMLRunRemindHandler();

            // 开始解析
            sParser.parse(inputStream, myHandler);

            // 解析完成之后，关闭流
            inputStream.close();
            //返回解析结果。
            List<RunRemind> list = myHandler.getRunRemindList();

            return myHandler.getRunRemindList();  //在这里返回解析之后的数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,String> getMapFromFile(Context mContext){
        int rawId =RessourceUtils.getRawId(mContext,"train_plan_02");
        Map<String,String> map = new HashMap<String,String>();
        try {

            InputStream inputStream = mContext.getResources().openRawResource(rawId);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream));
            String str = null;
            while (true) {
                str = reader.readLine();
                if(str!=null) {
                    // TODO: 18-3-8
                    map.put(str.trim(),str.trim());
                }else {
                    break;
                }
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     *  string by from resource ;
     *
     * @param mContext
     * @return
     */
    public static List<String> getMapFromReouceString(Context mContext,String fileName){
//        int rawId =RessourceUtils.getRawId(mContext,"templatestrings");
        try {
            InputStream inputStream  =mContext.getAssets().open(fileName);
//            InputStream inputStream = mContext.getResources().openRawResource(rawId);
            SAXParserFactory factory= SAXParserFactory.newInstance();
            //实例化SAX解析器。
            SAXParser sParser=factory.newSAXParser();
            //实例化工具类MyHandler，设置需要解析的节点
            XmlResourceHanlder myHandler=new XmlResourceHanlder();
            // 开始解析
            sParser.parse(inputStream, myHandler);
            // 解析完成之后，关闭流
            inputStream.close();
            //返回解析结果。
            List<String> list = myHandler.getListData();
           return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  static String getStringFromXmlByName(Context mContext ,String fileName){
        StringBuilder stringBuilder = new StringBuilder() ;
        try {
            InputStream inputStream  =mContext.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream));
            String str = null;
            while (true) {
                str = reader.readLine();
                if(str!=null) {
                    // TODO: 18-3-8
                    stringBuilder.append(str);
                }else {
                    break;
                }
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();

    }

    public static void writeStringToFile(Context mContext,String content,String fileName){

        String s = content;
        FileWriter fw = null;
        File f = new File("/data/data/"+mContext.getPackageName() +"/cache/"+fileName );
        try {
            if(!f.exists()){
                f.createNewFile();
            }else{
                f.delete();
            }
            fw = new FileWriter(f);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(s, 0, s.length()-1);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
