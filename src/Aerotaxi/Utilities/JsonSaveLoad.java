package Aerotaxi.Utilities;

import Aerotaxi.Core.Airplanes.Aircraft;
import Aerotaxi.Core.Airplanes.Bronze;
import Aerotaxi.Core.Airplanes.Gold;
import Aerotaxi.Core.Airplanes.Silver;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonSaveLoad {

    private static GsonBuilder gb = new GsonBuilder();

    private static Gson gson(){
        gb.registerTypeAdapter(ArrayList.class, new CustomDeserializer());
        gb.registerTypeAdapter(ArrayList.class, new CustomSerializer());

        /*
        gb.registerTypeAdapter(ArrayList.class, new CustomDeserializer<Aircraft>());
        gb.registerTypeAdapter(ArrayList.class, new CustomSerializer<Aircraft>());

        gb.registerTypeAdapter(ArrayList.class, new CustomDeserializer<FlightTicket>());
        gb.registerTypeAdapter(ArrayList.class, new CustomSerializer<FlightTicket>());
        */

        return gb.setPrettyPrinting().create();
    }

    public static <T> List<T> fromJsonToList(String path){
        List<T> list = new ArrayList<>();

        BufferedReader buff = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));

            list = gson().fromJson(reader, list.getClass());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }


    public static <T> void fromListToJson(String path,List<T> list){
        try{
            String json = gson().toJson(list);
            FileWriter file = null;

            file = new FileWriter(path);
            file.write(json);

            file.flush();
            file.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
