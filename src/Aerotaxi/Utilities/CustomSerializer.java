package Aerotaxi.Utilities;

import Aerotaxi.Airplanes.Aircraft;
import Aerotaxi.Airplanes.Bronze;
import Aerotaxi.Airplanes.Gold;
import Aerotaxi.Airplanes.Silver;
import Aerotaxi.Core.Admin;
import Aerotaxi.Core.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CustomSerializer<T> implements JsonSerializer<ArrayList<T>> {

    private static Map<String, Class> map = new TreeMap<String, Class>();

    static {
        map.put("User", User.class);
        map.put("Admin", Admin.class);
        map.put("Aircraft", Aircraft.class);
        map.put("Bronze", Bronze.class);
        map.put("Gold", Gold.class);
        map.put("Silver", Silver.class);
    }

    @Override
    public JsonElement serialize(ArrayList<T> src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        if (src == null)
            return null;
        else {
            JsonArray ja = new JsonArray();
            for (T bc : src) {
                Class c = map.get(bc.getClass().getSimpleName());
                if (c == null)
                    throw new RuntimeException("Unknow class: " + bc.getClass());
                ja.add(context.serialize(bc, c));

            }
            return ja;
        }
    }



}