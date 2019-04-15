package dao;

import mapper.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Order;
import pojo.Orders;
import pojo.Product;
import pojo.Productimage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrderDao {
    private static SqlSessionFactory factory;

    static {
        try {
            String resources = "mybatis-config.xml";
            InputStream in = Resources.getResourceAsStream(resources);
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据订单ID查询订单内的商品信息
     *
     * @param id
     * @return
     */
    public List<Orders> queryOrders(int id) {
        SqlSession session = factory.openSession();
        List<Orders> ordersList = null;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            ordersList = orderMapper.queryOrders(id);
        } finally {
            session.close();
        }
        return ordersList;
    }

    /**
     * 根据用户ID查询所有订单
     *
     * @param id
     * @return
     */
    public List<Order> queryOrder(int id) {
        SqlSession session = factory.openSession();
        List<Order> orderList = null;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            orderList = orderMapper.queryOrder(id);
        } finally {
            session.close();
        }
        return orderList;
    }

    /**
     * 根据用户ID查询所有订单
     *
     * @param id
     * @return
     */
    public List<Orders> uidQueryOrders(int id) {
        SqlSession session = factory.openSession();
        List<Orders> ordersList = null;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            ordersList = orderMapper.uidQueryOrders(id);
        } finally {
            session.close();
        }
        return ordersList;
    }

    /**
     * 根据orders的pid查询商品
     *
     * @param id
     * @return
     */
    public Product queryProduct(int id) {
        SqlSession session = factory.openSession();
        Product product = null;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            product = orderMapper.queryProduct(id);
        } finally {
            session.close();
        }
        return product;
    }

    /**
     * 根据商品pid查询图片路径
     *
     * @param id
     * @return
     */
    public Productimage queryProductimage(int id) {
        SqlSession session = factory.openSession();
        Productimage productimage = null;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            productimage = orderMapper.queryProductimage(id);
        } finally {
            session.close();
        }
        return productimage;
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    public int delOrder(int id) {
        SqlSession session = factory.openSession();
        int i = 0;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            i = orderMapper.delOrder(id);
            i += orderMapper.delOrders(id);
            if (i > 1) {
                session.commit();
            } else {
                session.rollback();
            }
        } finally {
            session.close();
        }
        return i;
    }

    /**
     * 删除订单的内容
     *
     * @param id
     * @return
     */
    public int delOrders(int id) {
        SqlSession session = factory.openSession();
        int i = 0;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            i = orderMapper.delOrders(id);
        } finally {
            session.close();
        }
        return i;
    }

    // 下面是xhs的

    /**
     * 往订单表插入信息
     *
     * @param order
     * @return
     */
    public int insertOrder(Order order) {
        SqlSession session = factory.openSession();
        int i = 0;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            i = orderMapper.insertOrder(order);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return i;
    }

    /**
     * 根据支付时间查询支付订单id
     *
     * @param payDate
     * @return
     */
    public int selectOrderId(String payDate) {
        SqlSession session = factory.openSession();
        int id = 0;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            id = orderMapper.selectOrdersId(payDate);
        } finally {
            session.close();
        }
        return id;
    }

    /**
     * 往orders插入数据
     *
     * @param orders
     * @return
     */
    public int insertOrders(Orders orders) {
        SqlSession session = factory.openSession();
        int i = 0;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            i = orderMapper.insertOrders(orders);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return i;
    }

}

