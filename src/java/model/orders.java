package model;

import java.sql.Timestamp;

public class orders {
    private int order_id, customer_id;
    private Timestamp order_date; // Thay đổi từ String sang Timestamp
    private double total_amount;
    private String status;

    public orders() {
    }

    public orders(int customer_id, Timestamp order_date, double total_amount, String status) {
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
    }

    public orders(int order_id, int customer_id, Timestamp order_date, double total_amount, String status) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}