package dao;

import mapper.ProductMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductDao {
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
     * 根据商品id查询商品信息
     * @param id
     * @return
     */
    public Product queryProduct(int id){
        SqlSession session = factory.openSession();
        Product product = null;
        try{
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            product = productMapper.queryProduct(id);
        }finally {
            session.close();
        }
        return  product;
    }

    /**
     * 查询商品信息到主页
     * @return
     */
    public List<Product> queryAll(){
        SqlSession session = factory.openSession();
        List<Product> products= null;
        try{
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            products = productMapper.queryAll();
        }finally {
            session.close();
        }
        return  products;
    }

    /**
     * 查询商品及图片路径到首页
     * @return
     */
    public List<Product> queryProductAndPath(){
        SqlSession session = factory.openSession();
        List<Product> products = null;
        try{
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            products = productMapper.queryProductAndPath();
        }finally {
            session.close();
        }
        return products;
    }
}
