package com.project.michael.base.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;

import java.lang.reflect.Type;

/**
 * Created by michael on 1/30/17.
 */

public class GsonUtils {

    private static final String TAG = "tmp-GsonUtils";

    public static String getJsonWithExposeFromObject(Object object, Type type) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(object, type);
    }

    public static String getJsonFromObject(Object object, Type type) {
        Gson gson = new Gson();
        return gson.toJson(object, type);
    }

    public static String getJsonFromObject(Object src) {
        Gson gson = new Gson();
        if (src == null) {
            return gson.toJson(JsonNull.INSTANCE);
        }
        return gson.toJson(src, src.getClass());
    }

    public static Object getObjectFromJson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public static <T> T getObjectFromJson(String json, Class<T> classOfT)throws JsonSyntaxException {
        Gson gson = new Gson();
        Object object = gson.fromJson(json, (Type) classOfT);
        return Primitives.wrap(classOfT).cast(object);
    }
}
