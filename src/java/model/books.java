
package model;


public class books {
    private int book_id;
    private String book_name;
    private int book_type_id;
    private double price;
    private int stock_quantity;
    private String description, imageURL;

    public books() {
    }

    public books(String book_name, int book_type_id, double price, int stock_quantity, String description, String imageURL) {
        this.book_name = book_name;
        this.book_type_id = book_type_id;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.description = description;
        this.imageURL = imageURL;
    }

    public books(int book_id, String book_name, int book_type_id, double price, int stock_quantity, String description, String imageURL) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_type_id = book_type_id;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.description = description;
        this.imageURL = imageURL;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getBook_type_id() {
        return book_type_id;
    }

    public void setBook_type_id(int book_type_id) {
        this.book_type_id = book_type_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

   
}
