package Aerotaxi.Utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CustomSerializer implements JsonSerializer<ArrayList<BaseClass>> {

    private static Map<String, Class> map = new TreeMap<String, Class>();

    static {
        map.put("BaseClass", BaseClass.class);
        map.put("ExtendedClass1", ExtendedClass1.class);
        map.put("ExtendedClass2", ExtendedClass2.class);
    }

    @Override
    public JsonElement serialize(ArrayList<BaseClass> src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        if (src == null)
            return null;
        else {
            JsonArray ja = new JsonArray();
            for (BaseClass bc : src) {
                Class c = map.get(bc.isA);
                if (c == null)
                    throw new RuntimeException("Unknow class: " + bc.isA);
                ja.add(context.serialize(bc, c));

            }
            return ja;
        }
    }
}