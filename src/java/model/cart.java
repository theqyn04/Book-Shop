package model;


public class cart {
    private int book_id;
    private String book_name;
    private int book_type_id;
    private double price;
    private int quantity;
    private String imageURL;
    private double discount;

    public cart() {
    }

    public cart(int book_id, String book_name, int book_type_id, double price, int quantity, String imageURL, double discount) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_type_id = book_type_id;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.discount = discount;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    
    
}
