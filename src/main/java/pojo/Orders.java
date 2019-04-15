package pojo;

import java.io.Serializable;
import java.util.List;

public class Orders implements Serializable {
    private static final long serialVersionUID = 6495494915064484247L;
    private int id,pid,oid,num;
    private Product product;
    private Productimage productimage;

    public Productimage getProductimage() {
        return productimage;
    }

    public void setProductimage(Productimage productimage) {
        this.productimage = productimage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", pid=" + pid +
                ", oid=" + oid +
                ", num=" + num +
                '}';
    }
    public Orders(Integer pid, Integer oid, Integer num) {
        this.pid = pid;
        this.oid = oid;
        this.num = num;
    }
    public Orders() {
    }

    public Orders(int id, int pid, int oid, int num) {
        this.id = id;
        this.pid = pid;
        this.oid = oid;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
