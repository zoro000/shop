package dao;

import mapper.SearchMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SearchDao {
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
     * 根据subtitle查询信息
     *
     * @param subTitle
     * @return
     */
    public List<Product> queryBySubTitle(String subTitle) {
        SqlSession session = factory.openSession();
        List<Product> products = null;
        try {
            SearchMapper searchMapper = session.getMapper(SearchMapper.class);
            products = searchMapper.selectBySubTitle(subTitle);
        } finally {
            session.close();
        }
        return products;
    }

    /**
     * 根据商品类别id查询
     * @param cid
     * @return
     */
    public List<Product> queryByCid(int cid) {
        SqlSession session = factory.openSession();
        List<Product> products = null;
        try {
            SearchMapper searchMapper = session.getMapper(SearchMapper.class);
            products = searchMapper.selectByCid(cid);
        } finally {
            session.close();
        }
        return products;
    }
}
