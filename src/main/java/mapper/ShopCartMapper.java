package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import pojo.Orderitem;
import pojo.Product;

import java.util.List;

public interface ShopCartMapper {
    public List<Orderitem> queryShop(int uid);
    public Product query(int id);
    public int delById(int id);
    /*@Update("update shop.orderitem set number=#{id} where id=#{number}")*/
    public int updNum(@Param("number") int number, @Param("id") int id);
    public int insertShopCart(@Param("cid") int cid,
                              @Param("number") int number,
                              @Param("uid") int uid);
}
