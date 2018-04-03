package com.doudou.cn.utils;

import android.content.Context;

/**
 * Created by jinliang on 18-3-7.
 */

public class RessourceUtils {

    public static final int TRAIN_PLAN_CATEGORY = 0 ;


    public static final int  TRAIN_PLAN_CATEGORY_XINSHOU = 1 ;
    // 5km
    public static final int  TRAIN_PLAN_CATEGORY_5KM  =2 ;

    // 10km
    public static final int TRAIN_PLAN_CATEGORY_10KM = 3 ;

    // 半马
    public static final  int TRAIN_PLAN_CATEGORY_BANMA =4 ;

    // 全马
    public static final  int TRAIN_PLAN_CATEGORY_QUANMA = 5 ;

    public static final int TRAIN_PLAN_RUN_REMIND =6 ;

    public static int getRawId(Context mContext , String rawFileName){
        int rawId = mContext.getResources().
                getIdentifier(rawFileName,"raw", mContext.getPackageName());
        return rawId;
    }

    /**
     * get RawId
     * @param mContext
     * @param categoryType
     * @return
     */
    public static int getFilePathByCategoryType(Context mContext  ,int categoryType){
        StringBuilder sb = new StringBuilder();
        switch (categoryType) {
            case TRAIN_PLAN_CATEGORY:
                sb.append("train_plan_category");
                break;
            case TRAIN_PLAN_CATEGORY_XINSHOU:
                sb.append("train_plan_category_xinshou");
                break;
            case TRAIN_PLAN_CATEGORY_5KM:
                sb.append("train_plan_category_5km");
                break;
            case TRAIN_PLAN_CATEGORY_10KM:
                sb.append("train_plan_category_10km");
                break;
            case TRAIN_PLAN_CATEGORY_BANMA:
                sb.append("train_plan_category_banma");
                break;
            case TRAIN_PLAN_CATEGORY_QUANMA:
                sb.append("train_plan_category_quanma");
                break;
            case TRAIN_PLAN_RUN_REMIND:
                sb.append("train_plan_runtype_writecopy");
                break;

        }
        return  getRawId(mContext,sb.toString());
    }

}
