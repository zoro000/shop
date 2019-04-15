package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.Order;
import pojo.Orders;
import pojo.Product;
import pojo.Productimage;

import java.util.List;

public interface OrderMapper {
    // 根据订单ID查询订单内的商品信息
    @Select("select * from shop.`orders` where oid=#{oid}")
    public List<Orders> queryOrders(@Param("oid") int id);

    // 根据用户ID查询订单内的商品信息
    @Select("select o.*,os.* from shop.order o,shop.orders os where o.id=os.oid and o.uid=#{id}")
    public List<Orders> uidQueryOrders(@Param("id") int id);

    // 根据用户ID查询所有订单
    @Select("select * from shop.`order` where uid=#{uid}")
    public List<Order> queryOrder(@Param("uid") int id);

    // 根据orders的pid查询商品
    @Select("select * from shop.product where id=#{pid}")
    public Product queryProduct(@Param("pid") int id);

    // 根据商品pid查询图片路径
    @Select("select * from shop.productimage where pid=#{id}")
    public Productimage queryProductimage(@Param("id") int id);

    // 删除订单
    @Delete("delete from shop.order where id=#{id}")
    public int delOrder(@Param("id") int id);

    // 删除订单下的内容
    @Delete("delete from shop.orders where oid=#{id}")
    public int delOrders(@Param("id") int id);

    // 下面是xhs的
    public int insertOrder(Order order);

    public int selectOrdersId(String payDate);

    public int insertOrders(Orders orders);
}
