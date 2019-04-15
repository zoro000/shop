package dao;

import mapper.AdminMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public class AdminDao implements Serializable {
    private static final long serialVersionUID = 8378432409897364770L;
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
     * 查询用户数量
     *
     * @return
     */
    public int userNum() {
        SqlSession session = factory.openSession();
        int i = 0;
        try {
            AdminMapper adminMapper = session.getMapper(AdminMapper.class);
            i = adminMapper.userNum();
        } finally {
            session.close();
        }
        return i;
    }

    /**
     * 查询订单数量
     *
     * @return
     */
    public int orderNum() {
        SqlSession session = factory.openSession();
        int i = 0;
        try {
            AdminMapper adminMapper = session.getMapper(AdminMapper.class);
            i = adminMapper.orderNum();
        } finally {
            session.close();
        }
        return i;
    }


    /**
     * 查询所有用户
     * @return
     */
    public List<User> selectAll(){
        SqlSession session = factory.openSession();
        List<User> userList= null;
        try {
            AdminMapper adminMapper = session.getMapper(AdminMapper.class);
            userList = adminMapper.selectAll();
        } finally {
            session.close();
        }
        return userList;
    }

    /**
     * 模糊查询用户
     * @param name
     * @return
     */
    public List<User> queryUser(String name){
        SqlSession session = factory.openSession();
        List<User> userList= null;
        try {
            AdminMapper adminMapper = session.getMapper(AdminMapper.class);
            userList = adminMapper.queryUser(name);
        } finally {
            session.close();
        }
        return userList;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public int delUser(int id){
        SqlSession session = factory.openSession();
        int i = 0;
        try {
            AdminMapper adminMapper = session.getMapper(AdminMapper.class);
            i = adminMapper.delUser(id);
        } finally {
            session.commit();
            session.close();
        }
        return i;
    }
}
