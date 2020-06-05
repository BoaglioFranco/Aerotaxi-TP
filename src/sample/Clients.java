package sample;

import java.io.Serializable;

public class Clients implements Serializable {

    private String name = "";
    private String surname= "";
    private int age;
    private String dni = ""; //TODO: Regex para validarlo

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(int totalGastado) {
        this.totalGastado = totalGastado;
    }

    private int totalGastado;
    //Mejor categoria de vuelo utilizado

    public Clients(){
        totalGastado = 0;
    }

    public Clients(String name, String surname, int age, String dni){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.dni = dni;
        totalGastado = 0;
    }

}
