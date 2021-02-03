package com.robot.baselibs.common.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.facebook.stetho.common.LogUtil;
import com.robot.baselibs.exception.LocalException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];

    private Type mType;

    private ParserConfig config;
    private int featureValues;
    private Feature[] features;

    FastJsonResponseBodyConverter(Type type, ParserConfig config, int featureValues,
                                  Feature... features) {
        mType = type;
        this.config = config;
        this.featureValues = featureValues;
        this.features = features;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T convert(ResponseBody value) throws IOException {
        try {
            String result = value.string();
            JSONObject jsonObject = new JSONObject(result);
            LogUtil.e(result);
            if (!jsonObject.isNull("status")) {
                int error = jsonObject.getInt("status");
                if (error != 1) {
                    Throwable throwable = new Throwable();
                    String msg = jsonObject.getString("msg");
                    throw new LocalException(throwable, Integer.valueOf(error),
                            msg);
                }
            }
            if (mType == String.class) {
                return (T) result;
            }
            return JSON.parseObject(result, mType, config, featureValues,
                    features != null ? features : EMPTY_SERIALIZER_FEATURES);

        } catch (LocalException e) {
            throw e;
        } catch (JSONException e) {
            throw new LocalException(e.getMessage());

        } finally {
            value.close();
        }
    }
}