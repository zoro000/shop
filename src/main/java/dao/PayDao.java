package dao;

import mapper.PayMapper;
import mapper.ShopCartMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Product;

import java.io.IOException;
import java.io.InputStream;

public class PayDao {
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
     * 根据购物车id查出商品id
     * @param odid
     * @return
     */
    public Product selectByOdid(int odid ){
        SqlSession session = factory.openSession();
        Product product =null;
        try{
            PayMapper payMapper = session.getMapper(PayMapper.class);
            product = payMapper.selectByOdid(odid);

        }finally {
            session.close();
        }
        return product;
    }
    /**
     * 根据id查询商品信息
     * @param pid
     * @return
     */
    public Product qeuryByPid(int pid){
        SqlSession session = factory.openSession();
        Product product = null;
        try{
            PayMapper payMapper = session.getMapper(PayMapper.class);
            product = payMapper.selectByPid(pid);
        }finally {
            session.close();
        }
        return product;
    }
}
