package com.shanxi.water.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CityUrlMap {
    public String getChineseByPinyin(String pinyin) {
        HashMap<String, String> cityMap = new HashMap<>();
        // 将拼音全小写连写作为键，市名作为值添加到 HashMap
        cityMap.put("xianshi", "西安市");
        cityMap.put("baojishi", "宝鸡市");
        cityMap.put("xianyangshi", "咸阳市");
        cityMap.put("tongchuanshi", "铜川市");
        cityMap.put("weinanshi", "渭南市");
        cityMap.put("yananshi", "延安市");
        cityMap.put("yulinshi", "榆林市");
        cityMap.put("ankangshi", "安康市");
        cityMap.put("hanzhongshi", "汉中市");
        cityMap.put("shangluoshi", "商洛市");

        return cityMap.get(pinyin);
    }
}
