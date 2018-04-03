package com.doudou.cn.testtrainplanassetpro;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.doudou.cn.utils.DayTrainPlan;
import com.doudou.cn.utils.ReplaceXmlDataTools;
import com.doudou.cn.utils.RessourceUtils;
import com.doudou.cn.utils.RunRemind;
import com.doudou.cn.utils.SAXUtils;
import com.doudou.cn.utils.TrainPlan;
import com.doudou.cn.xml.XmlTools;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private Map<String ,String>  map= new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int backHandRate  =new BigDecimal(  (double) 7*100/27).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                Log.i(TAG," backHandRata:"+ backHandRate);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handDataHandler();
                    }
                }).start();
            }
        });

        findViewById(R.id.btn_product_xml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmlTools.productXmlFile(MainActivity.this,"txt");
            }
        });


        findViewById(R.id.btn_compute_tennic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int total = 28 ;
                int foreHand = 28 ;
                int backHand = 0 ;
                int serve =0 ;

                int foreHandPercent = 0 ;
                int backHandPercent = 0 ;
                int mServerPercent = 0 ;


                foreHandPercent = foreHand * 100 / total;
                if ((backHand + serve) > 0) {
                    backHandPercent = (int) ((100 - foreHandPercent) * (backHand / (float) (backHand + serve)));
                }

                mServerPercent = 100 - foreHandPercent - backHandPercent;

                Log.i(TAG,"foreHandPercent:"+foreHandPercent
                        +",backHandPercent:"+backHandPercent+",mServerPercent:"+mServerPercent);


            }
        });


        findViewById(R.id.test_simple_data_tranfor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  Thread(new Runnable() {
                    @Override
                    public void run() {
//                        List<TrainPlan> listTrainPlan =
//                                SAXUtils.getTrainPlanFromXml(MainActivity.this, RessourceUtils.TRAIN_PLAN_CATEGORY);

                        ReplaceXmlDataTools.replaceXmlDataTrainPlan(MainActivity.this,null,"train_plan_category_xinshou.xml");
                    }
                }).start();

            }
        });

    }


    /**
     *  handlerData
     */
    private void handDataHandler(){

        map.clear();

        List<TrainPlan> listTrainPlan = SAXUtils.getTrainPlanFromXml(MainActivity.this, RessourceUtils.TRAIN_PLAN_CATEGORY);

        for (TrainPlan trainPlan: listTrainPlan) {
             map.put(trainPlan.getTitle(),trainPlan.getTitle());
             map.put(trainPlan.getCopyWrite(),trainPlan.getCopyWrite());
        }

        Log.i(TAG," listTrainPlanSize:"+ listTrainPlan.size());
        List<DayTrainPlan> listDayTrainPlan  =SAXUtils.getDayTrainPlanFromXml(MainActivity.this,RessourceUtils.TRAIN_PLAN_CATEGORY_5KM);

        for (DayTrainPlan dayTrainPlan: listDayTrainPlan ) {
            map.put(dayTrainPlan.getDesc(),dayTrainPlan.getDesc());
            map.put(dayTrainPlan.getStageDesc(),dayTrainPlan.getStageDesc());
        }

        Log.i(TAG," listDayTrainPlanSize:"+ listDayTrainPlan.size());

        List<DayTrainPlan> listDayTrainPlan_10  =SAXUtils.getDayTrainPlanFromXml(MainActivity.this,RessourceUtils.TRAIN_PLAN_CATEGORY_10KM);

        for (DayTrainPlan dayTrainPlan: listDayTrainPlan_10 ) {
            map.put(dayTrainPlan.getDesc(),dayTrainPlan.getDesc());
            map.put(dayTrainPlan.getStageDesc(),dayTrainPlan.getStageDesc());
        }

        Log.i(TAG," listDayTrainPlan_10Size:"+ listDayTrainPlan_10.size());

        List<DayTrainPlan> listDayTrainPlan_banma =SAXUtils.getDayTrainPlanFromXml(MainActivity.this,RessourceUtils.TRAIN_PLAN_CATEGORY_BANMA);

        for (DayTrainPlan dayTrainPlan: listDayTrainPlan_banma ) {
            map.put(dayTrainPlan.getDesc(),dayTrainPlan.getDesc());
            map.put(dayTrainPlan.getStageDesc(),dayTrainPlan.getStageDesc());
        }
        Log.i(TAG," listDayTrainPlan_banmaSize:"+ listDayTrainPlan_banma.size());

        List<DayTrainPlan> listDayTrainPlan_quanma =SAXUtils.getDayTrainPlanFromXml(MainActivity.this,RessourceUtils.TRAIN_PLAN_CATEGORY_QUANMA);

        for (DayTrainPlan dayTrainPlan: listDayTrainPlan_quanma ) {
            map.put(dayTrainPlan.getDesc(),dayTrainPlan.getDesc());
            map.put(dayTrainPlan.getStageDesc(),dayTrainPlan.getStageDesc());
        }
        Log.i(TAG," listDayTrainPlan_quanmaSize:"+ listDayTrainPlan_quanma.size());
        List<DayTrainPlan> listDayTrainPlan_xinshou =SAXUtils.getDayTrainPlanFromXml(MainActivity.this,RessourceUtils.TRAIN_PLAN_CATEGORY_XINSHOU);

        for (DayTrainPlan dayTrainPlan: listDayTrainPlan_xinshou ) {
            map.put(dayTrainPlan.getDesc(),dayTrainPlan.getDesc());
            map.put(dayTrainPlan.getStageDesc(),dayTrainPlan.getStageDesc());
        }
        Log.i(TAG," listDayTrainPlan_xinshouSize:"+ listDayTrainPlan_xinshou.size());
        List<RunRemind> runRmindList =SAXUtils.getRunRemindFromXml(MainActivity.this,RessourceUtils.TRAIN_PLAN_RUN_REMIND);

        for (RunRemind dayTrainPlan: runRmindList ) {
           map.put(dayTrainPlan.getRunType(),dayTrainPlan.getRunType());
           map.put(dayTrainPlan.getCopyWrite(),dayTrainPlan.getCopyWrite());
           map.put(dayTrainPlan.getTrainContent(),dayTrainPlan.getTrainContent());
        }
        Log.i(TAG," runRmindListSize:"+ runRmindList.size());
        Log.i(TAG,"  mapSize:"+ map.keySet().size());

        Map<String,String> fileMap =SAXUtils.getMapFromFile(MainActivity.this);
        printMapData(fileMap);

    }


    private void handlerRepeatData(){



    }
    private void printMapData(final Map<String ,String>  map){

        Log.i(TAG,"-------------size-----------" + map.keySet().size());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (String s : map.keySet()) {
                    Log.i(TAG,""+map.get(s));
                }
            }
        });




//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });


    }

}
