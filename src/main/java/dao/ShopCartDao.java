package dao;

import mapper.ShopCartMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Orderitem;
import pojo.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ShopCartDao {

    private static SqlSessionFactory factory;
    static {
        String resources = "mybatis-config.xml";
        try {
            InputStream in = Resources.getResourceAsStream(resources);
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据用户id查出购物车
     * @param uid
     * @return
     */
    public List<Orderitem> queryShop(int uid){
        List<Orderitem> orderitems = null;
        SqlSession session = factory.openSession();
        try{
            ShopCartMapper shopCartMapper = session.getMapper(ShopCartMapper.class);
            orderitems = shopCartMapper.queryShop(uid);
        }finally {
            session.close();
        }
        return orderitems;
    }

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    public Product qeuryBy(int id){
        SqlSession session = factory.openSession();
        Product product = null;
        try{
            ShopCartMapper shopCartMapper = session.getMapper(ShopCartMapper.class);
            product = shopCartMapper.query(id);
        }finally {
            session.close();
        }
        return product;
    }

    /**
     *
     * @param id
     */
    public int  delteById(int id ){
        SqlSession session = factory.openSession();
        int i =0;
        try{
            ShopCartMapper shopCartMapper = session.getMapper(ShopCartMapper.class);
            i=shopCartMapper.delById(id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return i;
    }

    /**
     * 根据用户id修改number
     * @param number
     * @param id
     * @return
     */
    public int updNum( int number, int id){
        SqlSession session = factory.openSession();
        int i = 0;
        try{
            ShopCartMapper shopCartMapper = session.getMapper(ShopCartMapper.class);
            i=shopCartMapper.updNum(number,id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return i;
    }

    /**
     * 往购物车表插入数据
     * @param cid
     * @param number
     * @param uid
     * @return
     */
    public int insertShopCart(int cid,int number,int uid){
        SqlSession session = factory.openSession();
        int i = 0;
        try{
            ShopCartMapper shopCartMapper = session.getMapper(ShopCartMapper.class);
            i = shopCartMapper.insertShopCart(cid,number,uid);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return i;
    }
}
