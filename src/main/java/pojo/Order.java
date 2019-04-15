package pojo;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 7850916083422199297L;

    private int id,addressId,status,uid;
    private String payDate;
    private Double price;
    private List<Orders> orders;

    @Override
    public String toString() {
        return "Order{}";
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Order() {
    }

    public Order(int id, int addressId, int status, int uid, String payDate, Double price) {
        this.id = id;
        this.addressId = addressId;
        this.status = status;
        this.uid = uid;
        this.payDate = payDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
