package pojo;

import java.io.Serializable;

public class LunBo implements Serializable {
    private static final long serialVersionUID = 6150530660432083333L;
    private int id, pid;
    private String type;

    public LunBo() {
    }

    public LunBo(int id, int pid, String type) {
        this.id = id;
        this.pid = pid;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Productimage{" +
                "id=" + id +
                ", pid=" + pid +
                ", type='" + type + '\'' +
                '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
