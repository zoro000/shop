package pojo;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 4988695594299525055L;
    private int id, uid, oid;
    private String address, name, phone;

    public Address(int id, int uid, int oid, String address, String name, String phone) {
        this.id = id;
        this.uid = uid;
        this.oid = oid;
        this.address = address;
        this.name = name;
        this.phone = phone;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
