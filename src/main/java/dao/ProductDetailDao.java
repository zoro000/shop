package dao;

import mapper.ProductDetailMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductDetailDao {
    private static SqlSessionFactory factory;
    static {
        String resource="mybatis-config.xml";
        try {
            InputStream in = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    public Product queryById(int id){
        SqlSession session = factory.openSession();
        Product product = null;
        try{
            ProductDetailMapper productDetailMapper = session.getMapper(ProductDetailMapper.class);
            product = productDetailMapper.queryById(id);
        }finally {
            session.close();
        }
        return  product;
    }

    /**
     * 根据商品id查出商品cid
     * @param id
     * @return
     */
    public int queryCid(int id){
        SqlSession session = factory.openSession();
        int cid = 0;
        try{
            ProductDetailMapper productDetailMapper = session.getMapper(ProductDetailMapper.class);
            cid = productDetailMapper.queryCid(id);
        }finally {
            session.close();
        }
        return cid;
    }

    /**
     * 根据商品cid查出某一类产品
     * @param cid
     * @return
     */
    public List<Product> queryByCid(int cid){
        SqlSession session = factory.openSession();
        List<Product> products = null;
        try{
            ProductDetailMapper productDetailMapper = session.getMapper(ProductDetailMapper.class);
            products = productDetailMapper.queryByCid(cid);
        }finally {
            session.close();
        }
        return products;
    }

}
