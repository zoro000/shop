package pojo;

import java.io.Serializable;

public class Orderitem implements Serializable {
    private static final long serialVersionUID = 428843987822438619L;
    private int id,cid,number,uid;

    @Override
    public String toString() {
        return "Orderitem{" +
                "id=" + id +
                ", cid=" + cid +
                ", number=" + number +
                ", uid=" + uid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Orderitem(int id, int cid, int number, int uid) {

        this.id = id;
        this.cid = cid;
        this.number = number;
        this.uid = uid;
    }

    public Orderitem() {

    }
}
