package Aerotaxi.Utilities;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CustomDeserializer implements JsonDeserializer<List<BaseClass>> {

    private static Map<String, Class> map = new TreeMap<String, Class>();

    static {
        map.put("BaseClass", BaseClass.class);
        map.put("ExtendedClass1", ExtendedClass1.class);
        map.put("ExtendedClass2", ExtendedClass2.class);
    }

    public List<BaseClass> deserialize(JsonElement json, Type typeOfT,
                                       JsonDeserializationContext context) throws JsonParseException {

        List list = new ArrayList<BaseClass>();
        JsonArray ja = json.getAsJsonArray();

        for (JsonElement je : ja) {

            String type = je.getAsJsonObject().get("isA").getAsString();
            Class c = map.get(type);
            if (c == null)
                throw new RuntimeException("Unknow class: " + type);
            list.add(context.deserialize(je, c));
        }

        return list;

    }

}