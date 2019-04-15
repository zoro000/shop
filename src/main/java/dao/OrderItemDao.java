package dao;

import mapper.OrderItemMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class OrderItemDao {
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
    public int selectCountItem(int uid){
        SqlSession session = factory.openSession();
        int i = 0;
        try{
            OrderItemMapper orderItemMapper = session.getMapper(OrderItemMapper.class);
            i = orderItemMapper.selectCountItem(uid);
        }finally {
            session.close();
        }
        return i;
    }
}
