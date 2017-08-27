package com.huazidev.btsearcher.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static final Gson sGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'")
            .create();

    public static Gson getGson() {
        return sGson;
    }

    /**
     * 字符串List 转化为 Json字符串
     */
    public static String stringListToJson(List<String> list) {
        if (list != null) {
            return getGson().toJson(list);
        } else {
            return "[]";
        }
    }

    /**
     * Json字符串 转化为 字符串List
     */
    public static List<String> jsonToStringList(String json) {
        if (json != null) {
            Type stringListType = new TypeToken<List<String>>() {
            }.getType();
            List<String> stringList = getGson().fromJson(json, stringListType);
            if (stringList != null) {
                return stringList;
            }
        }
        return new ArrayList<>();
    }

    public static Map<String, Object> toMap(String json) {
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        return JsonUtils.getGson().fromJson(json, mapType);
    }
}
