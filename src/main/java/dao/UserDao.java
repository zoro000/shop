package dao;

import mapper.LoginMapper;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Address;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDao {
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
     * 根据用户名查询用户信息
     *
     * @param name
     * @return
     */
    public User selectUser(String name) {
        SqlSession session = factory.openSession();
        User user = new User();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            user = userMapper.selectUser(name);
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * 修改用户信息 (账号 密码 手机号 邮箱)
     *
     * @param name
     * @param password
     * @param phone
     * @param email
     * @param id
     * @return
     */
    public int updUser(String name,
                       String password,
                       String phone,
                       String email,
                       int id) {
        SqlSession session = factory.openSession();
        int flwRow = 0;
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            flwRow = userMapper.updUser(name,
                    password,
                    phone,
                    email,
                    id);
            session.commit();
        } finally {
            session.close();
        }
        return flwRow;
    }


    /**
     * 用户注册
     *
     * @param name
     * @param password
     * @param phone
     * @param email
     * @return
     */
    public int insertUser(String name,
                          String password,
                          String phone,
                          String email) {
        SqlSession session = factory.openSession();
        int flwRow = 0;
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            flwRow = userMapper.insertUser(name,
                    password,
                    phone,
                    email);
            session.commit();
        } finally {
            session.close();
        }
        return flwRow;
    }

    /**
     * 新增地址
     *
     * @param address
     * @param uid
     * @param name
     * @param phone
     * @return
     */
    public int saveAddress(String address, int uid, String name, String phone) {
        SqlSession session = factory.openSession();
        int flwRow = 0;
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            flwRow = userMapper.saveAddress(address, uid, name, phone);
            session.commit();
        } finally {
            session.close();
        }
        return flwRow;
    }

    /**
     * 忘记密码 查询
     *
     * @param name
     * @param answer
     * @return
     */
    public User queryPwd(String name, String answer) {
        SqlSession session = factory.openSession();
        User user = new User();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            user = userMapper.queryPwd(name, answer);
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * 默认地址查询
     *
     * @return
     */
    public List<Address> address(int uid) {
        SqlSession session = factory.openSession();
        List<Address> addressList = null;
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            addressList = userMapper.address(uid);
        } finally {
            session.close();
        }
        return addressList;
    }

    /**
     * 地址删除
     *
     * @param id
     * @return
     */
    public int delAddress(int id) {
        SqlSession session = factory.openSession();
        int flwRow = 0;
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            flwRow = userMapper.delAddress(id);
            session.commit();
        } finally {
            session.close();
        }
        return flwRow;
    }

    /**
     * 新增问题
     * @param problem
     * @param answer

     * @return
     */
    public int saveQ( String problem,String answer,int id){
        SqlSession session = factory.openSession();
        int flwRow = 0;
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            flwRow = userMapper.saveQ(problem,answer,id);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return flwRow;
    }
}

