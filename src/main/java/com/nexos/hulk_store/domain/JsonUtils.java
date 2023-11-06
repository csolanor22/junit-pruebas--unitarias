/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulk_store.domain;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Carlos Montealegre
 */
public class JsonUtils {

    private JsonUtils() {
    }

    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(DateUtils.SIMPLE_DATE_FORMAT)
            .setPrettyPrinting()
            .create();

    private static final Gson GSON_IDENTITY = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setDateFormat(DateUtils.SIMPLE_DATE_FORMAT)
            .setPrettyPrinting()
            .create();

    private static final Gson GSON_COMPACT = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(DateUtils.SIMPLE_DATE_FORMAT)
            .create();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static String toJsonIdentity(Object obj) {
        return GSON_IDENTITY.toJson(obj);
    }

    public static String toJsonCompact(Object obj) {
        return GSON_COMPACT.toJson(obj);
    }

    public static <T extends Object> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }
}
