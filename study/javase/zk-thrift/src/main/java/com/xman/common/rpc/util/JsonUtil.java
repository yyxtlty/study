//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xman.common.rpc.util;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public JsonUtil() {
    }

    public Map<String, Object> toMap(String content) {
        try {
            return (Map)MAPPER.readValue(content, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception var3) {
            throw new RuntimeException("error decode json for " + content, var3);
        }
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        } else {
            try {
                return MAPPER.writeValueAsString(obj);
            } catch (Exception var2) {
                throw new RuntimeException("error encode json for " + obj, var2);
            }
        }
    }

    public static byte[] toBytes(Object obj) {
        try {
            return MAPPER.writeValueAsBytes(obj);
        } catch (Exception var2) {
            throw new RuntimeException("error encode json for " + obj, var2);
        }
    }

    public static <T> T toObject(String json, Class<T> cls) {
        if (json == null) {
            return null;
        } else {
            try {
                return MAPPER.readValue(json, cls);
            } catch (Exception var3) {
                throw new RuntimeException("error decode json to " + cls, var3);
            }
        }
    }

    public static <T> T toObject(byte[] jsonBytes, Class<T> cls) {
        try {
            return MAPPER.readValue(jsonBytes, cls);
        } catch (Exception var3) {
            throw new RuntimeException("error decode json to " + cls, var3);
        }
    }

    public static <T> T toObject(String json, TypeReference typeReference) {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (Exception var3) {
            throw new RuntimeException("error decode json to " + typeReference, var3);
        }
    }

    public static <T> T toObject(byte[] jsonBytes, TypeReference typeReference) {
        try {
            return MAPPER.readValue(jsonBytes, typeReference);
        } catch (Exception var3) {
            throw new RuntimeException("error decode json to " + typeReference, var3);
        }
    }

    public static Map<String, Object> readMap(String json) {
        try {
            return (Map)MAPPER.readValue(json, HashMap.class);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    static {
        MAPPER.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.setSerializationInclusion(Inclusion.NON_NULL);
    }
}
