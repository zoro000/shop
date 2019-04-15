package mapper;

import pojo.Product;

public interface PayMapper {

    public Product selectByPid(int pid);

    public Product selectByOdid(int odid);
}
