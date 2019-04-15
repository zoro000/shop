package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Product;

import java.util.List;

public interface ProductDetailMapper {
    public Product queryById(int id);//根据商品id联表查出商品信息和地址
    public int queryCid(int id);//根据商品id查出商品cid
    public List<Product> queryByCid(int cid);//根据商品cid查出某一类产品

}
