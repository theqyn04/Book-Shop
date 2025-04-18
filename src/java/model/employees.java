
package model;


public class employees {
    private int employee_id;
    private String full_name, phone_number, email, address;
    private int store_id;
    private String username, password;

    public employees() {
    }

    public employees(int employee_id, String full_name, String phone_number, String email, String address, int store_id, String username, String password) {
        this.employee_id = employee_id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.store_id = store_id;
        this.username = username;
        this.password = password;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
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
