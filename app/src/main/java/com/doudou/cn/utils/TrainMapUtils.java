package com.doudou.cn.utils;

import java.util.HashMap;

/**
 * Created by jinliang on 18-4-3.
 */

public class TrainMapUtils {

    private  static HashMap<String,String> map = new HashMap<>();

    static {
        map.put("进阶跑 3.2km","");
        map.put("完成一个10km","");
        map.put("节奏跑 8km","");
        map.put("基础跑 9.7km","");
        map.put("基础跑 8km","");
        map.put("法特莱克跑 4.8km","");
        map.put("慢跑 4km","");
        map.put("维持均匀的速度进行跑步训练。推荐心率%1$d-%2$dbpm","");
        map.put("基础跑","");
        map.put("慢跑 3.2km","");
        map.put("法特莱克跑 16km","");
        map.put("休息日","");
        map.put("新手上路","");
        map.put("进阶跑 4.8km","");
        map.put("节奏跑","");
        map.put("慢跑 4.4km","");
        map.put("交叉训练可以提高水平和恢复精力。推荐心率%1$d-%2$dbpm","");
        map.put("间歇跑（短周期)","");
        map.put("适于全马备赛者 。此训练可以辅助你预测参赛表现，并帮助你规划自己的配速和比赛策略。","");
        map.put("以快跑800m，步行800m为周期完成。推荐心率%1$d-%2$dbpm","");
        map.put("以慢跑800m，步行200m为周期完成。推荐心率%1$d-%2$dbpm","");
        map.put("完成一个5km","");
        map.put("骑行20-30分钟","");
        map.put("适于入门跑者。此训练可以帮助你获得更佳的运动状态。","");
        map.put("法特莱克跑 14.5km","");
        map.put("法特莱克跑 28.9km","");
        map.put("节奏跑 4.8km","");
        map.put("间歇跑（长） 12.8km","");
        map.put("10公里","");
        map.put("完成一个13.2km","");
        map.put("坡度跑 16km","");
        map.put("全程马拉松","");
        map.put("间歇跑（长） 9.6km","");
        map.put("基础锻炼","");
        map.put("山坡跑","");
        map.put("半程马拉松","");
        map.put("休息一下，今天无需进行训练。距训练开始还有%1$d天","");
        map.put("游泳可以帮助恢复体能，完成训练后点击此处标记为完成","");
        map.put(" 慢跑","");
        map.put("可随意切换强度的间歇跑步训练。推荐心率%1$d-%2$dbpm","");
        map.put("法特莱克跑","");
        map.put("节奏跑 9.7km","");
        map.put("坡度跑 20.9km","");
        map.put("间歇跑（短） 8km","");
        map.put("基础跑 6.5km","");
        map.put("5公里","");
        map.put("适于从步行到慢跑的初跑者。此训练可以帮助你循序渐进的适应跑步运动。","");
        map.put("坡度跑 9.7km","");
        map.put("恢复跑","");
        map.put("法特莱克跑 24.1km","");
        map.put("休息","");
        map.put("法特莱克跑 3.2km","");
        map.put("间歇跑（长周期)","");
        map.put("坡度跑 6.5km","");
        map.put("法特莱克跑 32.1km","");
        map.put("节奏跑 6.5km","");
        map.put("针对性阶段","");
        map.put("节奏跑 9.1km","");
        map.put("恢复跑 3.2km","");
        map.put("减量阶段","");
        map.put("以快跑2.4km，步行800m为周期完成。推荐心率%1$d-%2$dbpm","");
        map.put("慢跑 2.4km","");
        map.put("间歇跑（短） 4.8km","");
        map.put("适于进阶跑者 。此训练有助于提升你的有氧适能和肌肉力量。","");
        map.put("以轻松的慢跑完成训练。推荐心率%1$d-%2$dbpm","");
        map.put("坡度跑 4.8km","");
        map.put("法特莱克跑 19.3km","");
        map.put("适于半马备赛者 。此训练有助于提高你长时间不间断跑的能力。","");
        map.put("慢跑 2km","");
        map.put("完成一个半马21.2km","");
        map.put("请选择在有坡度的路段训练。推荐心率%1$d-%2$dbpm","");
        map.put("法特莱克跑 6.5km","");
        map.put("基础跑 3.2km","");
        map.put("游泳 20-30分钟","");
        map.put("进阶跑","");
        map.put("基础跑 4.8km","");
        map.put("跑步","");
        map.put("进阶跑 8km","");
        map.put("恢复跑 6.5km","");
        map.put("骑行","");
        map.put("进阶跑 9.7km","");
        map.put("恢复跑 4.8km","");
        map.put("坡度跑 8km","");
        map.put("间歇跑（短） 6.4km","");
        map.put("跑到习惯的速度后再进行加速。推荐心率%1$d-%2$dbpm","");
        map.put("游泳","");
        map.put("慢跑 2.8km","");
        map.put("完成一个全马42.5km","");
        map.put("以仍能交谈的速度进行跑步训练。推荐心率%1$d-%2$dbpm","");
        map.put("慢跑 5km","");


    }
    /**
     * @param key
     * @return
     */
    String getValueByKey(String key){
        String value = map.get(key);
        if(value==null){
            return "" ;
        }
        return value;
    }
}
