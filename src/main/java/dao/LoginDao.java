package dao;

import mapper.LoginMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class LoginDao  {
    private static SqlSessionFactory factory;
    static {
        try {
            String resources= "mybatis-config.xml";
            InputStream in=Resources.getResourceAsStream(resources);
            factory=new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User selectLogin(String name,String password){
        SqlSession session=factory.openSession();
        User user=null;
        try{
            LoginMapper loginMapper=session.getMapper(LoginMapper.class);
            user=loginMapper.selectLogin(name,password);
        } finally {
            session.close();
        }
        return  user;
    }
}
