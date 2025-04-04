package model;

public class Buyer {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    public Buyer(int id, String name, String email, String password,String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role=role;
    }


    public Buyer(String buyerName, String buyerEmail, String buyerPassword) {
        this.name = buyerName;
        this.email = buyerEmail;
        this.password = buyerPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
