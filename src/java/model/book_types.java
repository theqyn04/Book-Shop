
package model;


public class book_types {
    private int book_type_id;
    private String book_type_name, description;

    public book_types() {
    }

    public book_types(int book_type_id, String book_type_name, String description) {
        this.book_type_id = book_type_id;
        this.book_type_name = book_type_name;
        this.description = description;
    }

    public int getBook_type_id() {
        return book_type_id;
    }

    public void setBook_type_id(int book_type_id) {
        this.book_type_id = book_type_id;
    }

    public String getBook_type_name() {
        return book_type_name;
    }

    public void setBook_type_name(String book_type_name) {
        this.book_type_name = book_type_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
