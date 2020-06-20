package Aerotaxi.Core;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class User implements Serializable  {

    protected String username = "";
    protected String password = "";

    private String name = "";
    private String surname= "";
    private int age;
    private String dni = "";

    private int totalSpent;
    private String bestClass = "";

    protected String isA;


    public User(){
        totalSpent = 0;
        bestClass = "None";
        isA = "User";
    }

    public User(String username, String password, String name, String surname, int age, String dni) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.dni = dni;
        totalSpent = 0;
        bestClass = "None";
        isA = "User";
    }


    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        boolean isValid = name.matches("\\w+[\\w ]{4,20}");
        if(isValid)
            this.name = name;
        return isValid;
    }

    public String getSurname() {
        return surname;
    }

    public boolean setSurname(String surname) {
        boolean isValid = surname.matches("\\w+[\\w ]{4,20}");
        if(isValid)
            this.surname = surname;
        return isValid;

    }


    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) {
        boolean isValid = username.matches("\\w{4,20}");
        if(isValid)
            this.username = username;
        return isValid;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        boolean isValid = password.matches("\\w{4,20}");
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

    public int getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(int totalSpent){
        this.totalSpent = totalSpent;
    }

    public String getBestClass() {
        return bestClass;
    }

    public void setBestClass(String bestClass) {
        this.bestClass = bestClass;
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

    @Override
    public String toString() {
        return "Username: " + username +
                "\nPassword: " + password +
                "\nname: " + name +
                "\nlastname: " + surname +
                "\nAge: " + age +
                "\nDNI : " + dni +
                "\n@Class : " + isA + " -x";
    }
}
