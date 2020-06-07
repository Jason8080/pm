package com.gm.pm.kit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Jason
 */
public class JsonKit {
    private static final ObjectMapper om = new ObjectMapper();

    public static <T> T toObject(String json, Class<T> t) {
        try {
            return om.readValue(json, t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toObject(byte[] bytes, Class<T> t) {
        try {
            return om.readValue(bytes, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
