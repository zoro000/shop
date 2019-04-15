package dao;

import mapper.CarouselListMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Productimage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ListDao {
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
     * 轮播查询数据
     * @return
     */
    public List<Productimage> queryCarousel(){
        SqlSession session = factory.openSession();
        List<Productimage> productimages = null;
        try{
            CarouselListMapper listMapper = session.getMapper(CarouselListMapper.class);
            productimages = listMapper.queryCarousel();
        }finally {
            session.close();
        }
        return productimages;
    }

}
