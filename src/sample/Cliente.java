package sample;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String name = "";
    private String surname= "";
    private int age;
    private String dni = ""; //TODO: Regex para validarlo
    private int totalGastado;
    //Mejor categoria de vuelo utilizado

    public Cliente(){
        totalGastado = 0;
    }

    public  Cliente(String name, String surname, int age, String dni){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.dni = dni;
        totalGastado = 0;
    }

}
