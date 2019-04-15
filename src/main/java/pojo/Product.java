package pojo;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 8021004414580505586L;
    private int id,count;
    private String name,subTitle,orignalPrice,promotePrice,cid;
    private String type;
    private Integer number;
    private Integer otid;

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", orignalPrice='" + orignalPrice + '\'' +
                ", promotePrice='" + promotePrice + '\'' +
                ", cid='" + cid + '\'' +
                ", type='" + type + '\'' +
                ", number=" + number +
                ", otid=" + otid +
                '}';
    }

    public Product(int id, int count, String name, String subTitle, String orignalPrice, String promotePrice, String cid, String type, Integer number, Integer otid) {
        this.id = id;
        this.count = count;
        this.name = name;
        this.subTitle = subTitle;
        this.orignalPrice = orignalPrice;
        this.promotePrice = promotePrice;
        this.cid = cid;
        this.type = type;
        this.number = number;
        this.otid = otid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOtid() {
        return otid;
    }

    public void setOtid(Integer otid) {
        this.otid = otid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getOrignalPrice() {
        return orignalPrice;
    }

    public void setOrignalPrice(String orignalPrice) {
        this.orignalPrice = orignalPrice;
    }

    public String getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(String promotePrice) {
        this.promotePrice = promotePrice;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
