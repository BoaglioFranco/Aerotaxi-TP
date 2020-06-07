package sample;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable {

    private String username = "";
    private String password= "";
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

    public User(){
        totalGastado = 0;
    }

    public User(String name, String surname, int age, String dni){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.dni = dni;
        totalGastado = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
