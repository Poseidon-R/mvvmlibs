package com.robot.baselibs.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.robot.baselibs.model.BasePageBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class JsonUtil {

    public static JsonArray getJsonArrayFromMap(Object object) {
        Gson gson = getGson();
        String jsonStr = gson.toJson(object);
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(jsonStr).getAsJsonArray();
        return jsonArray;
    }


//    public static JSONObject getJsonObjectFromMap(Object object) {
//        return new JSONObject((Map) object);
//    }

    public static JSONObject getJsonObjectFromMap(Object object) {
        try {
            return new JSONObject(getGson().toJson(object));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> Object getObject(Object object, Class<T> classOfT) {
        Gson gson = getGson();
        String jsonStr = gson.toJson(object);
        return gson.fromJson(jsonStr, (Type) classOfT);
    }


    public static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        BooleanSerializer serializer = new BooleanSerializer();
        builder.registerTypeAdapter(Boolean.class, serializer);
        builder.registerTypeAdapter(boolean.class, serializer);
        return builder.create();
    }

    public static <T> ArrayList<T> jsonToList(String json, Class<T> classOfT) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjs = new Gson().fromJson(json, type);

        ArrayList<T> listOfT = new ArrayList<>();
        for (JsonObject jsonObj : jsonObjs) {
            listOfT.add(new Gson().fromJson(jsonObj, classOfT));
        }

        return listOfT;
    }

    public static BasePageBean getPageBeanFromMap(Object object) {
        Gson gson = getGson();
        String jsonStr = gson.toJson(object);
        return gson.fromJson(jsonStr, BasePageBean.class);
    }


    /**
     * @param pageBean
     * @return
     */
    public static <T> List<T> getListData(BasePageBean pageBean, Class<T> classOfT) {
        List list = new ArrayList();
        Gson gson = getGson();
        for (JsonElement obj : pageBean.getList()) {
            list.add(gson.fromJson(obj, (Type) classOfT));
        }
        return list;
    }


}
