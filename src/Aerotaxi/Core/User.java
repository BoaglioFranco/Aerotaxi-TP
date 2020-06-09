package Aerotaxi.Core;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class User implements Serializable {

    private String username = "";
    private String password = "";

    private String name = "";
    private String surname= "";
    private int age;
    private String dni = "";
    private int totalGastado;
    //Mejor categoria de vuelo utilizado

    public User(){
        totalGastado = 0;
    }

    public User(String username, String password, String name, String surname, int age, String dni) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.dni = dni;
        totalGastado = 0;
    }


    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        boolean isValid = name.length() > 1 && name.length() < 21;
        if(isValid)
            this.name = name;
        return isValid;
    }

    public String getSurname() {
        return surname;
    }

    public boolean setSurname(String surname) {
        boolean isValid = surname.length() > 1 && surname.length() < 21;
        if(isValid)
            this.surname = surname;
        return isValid;

    }


    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) {
        boolean isValid = username.length() > 4 && username.length() < 21;
        if(isValid)
            this.username = username;
        return isValid;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        boolean isValid = password.length() > 4 && password.length() < 21;
        if(isValid)
            this.password = password;
        return isValid;
    }

    public int getAge() {
        return age;
    }

    public boolean setAge(int age) {
        boolean isValid = age > 9 && age < 99;
        if(isValid) this.age = age;

        return isValid;
    }

    public String getDni() {
        return dni;
    }

    private String dniFormatter(String dni){ //para ponerle puntitos al dni asi quedan lindos, terrible huevada
        NumberFormat nf = NumberFormat.getInstance(new Locale("es", "ES"));
        return nf.format(Integer.parseInt(dni));
    }


    public boolean setDni(String dni) { //DNI puede tener 7 u 8 digitos, con o sin puntos
        boolean isValid = dni.matches("(\\d{1,2}\\.\\d{3}\\.\\d{3})|\\d{7,8}");
        if(isValid){
            if(dni.length() == 7 || dni.length() == 8) {
                this.dni = dniFormatter(dni);
            }
            else{
                this.dni = dni;
            }
        }
        return isValid;
    }

    public int getTotalGastado() {
        return totalGastado;
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

}
