package Aerotaxi.Core;


public class Admin extends User {

    public Admin(String username,String password) {
    this.username = username;
    this.password = password;
    this.isA = "Admin";
    }

    @Override
    public String toString() {
        return "Username : " +username +
                " Password : "+password +
                "@Class : "+ isA + " ---";
    }
}
