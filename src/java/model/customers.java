
package model;


public class customers {
    private int customer_id;
    private String full_name, address, phone_number, email, username, password;

    public customers() {
    }

    public customers(int customer_id, String full_name, String address, String phone_number, String email, String username, String password) {
        this.customer_id = customer_id;
        this.full_name = full_name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public customers(String full_name, String address, String phone_number, String email, String username, String password) {
        this.full_name = full_name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    
    
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
