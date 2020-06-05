package sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.lang.reflect.Type;

public class DataWarehouse {
    private List<Clients> clientList = new LinkedList<>();

    public DataWarehouse(){};

    public void fromJsontoUserslist() {
        try{
            /// Reader para ubicar el path de mi archivo JSON.
        Reader reader = Files.newBufferedReader(Paths.get("src/sample/Clients.JSON"));
            /// Esto es para obtener el tipo exacto de la Linkedlist ya que puede ser generico.
        Type userListType = new TypeToken<LinkedList<Clients>>(){}.getType();

        this.clientList = new Gson().fromJson(reader,userListType);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
