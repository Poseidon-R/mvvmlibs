package com.robot.baselibs.utils.json;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/10/10.
 */

public class JSONParseUtils {
    /**
     * Gson对象
     */
    private volatile static Gson gson;

    /**
     * 不允许实例
     */
    protected JSONParseUtils() {
        throw new AssertionError();
    }

    /**
     * 初始化Gson
     */
    private static void initGson() {
        if (gson == null) {
            synchronized (JSONParseUtils.class) {
                if (gson == null) {
                    gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                }
            }
        }
    }

    /**
     * 解析jsonString
     *
     * @param <T>
     * @param jsonString
     * @param clsBean
     * @return
     */
    public static <T> T parse(String jsonString, Class<T> clsBean) {
        initGson();
        try {
            return (T) gson.fromJson(jsonString, clsBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析jsonString
     *
     * @param jsonString
     * @param clsBean
     * @return
     */
    public static <T> List<T> parseList(String jsonString, Class<T> clsBean) {

        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }

        initGson();
        List<T> result = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                String strJ = jsonArray.getString(i);
                result.add(parse(strJ, clsBean));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> getMapForJson(String jsonStr) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonStr);

            Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转jsonstring
     *
     * @param o
     */
    public static String objectToString(Object o) {
        initGson();
        String result = "";
        try {
            result = gson.toJson(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
