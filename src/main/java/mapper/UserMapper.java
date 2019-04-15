package mapper;

import org.apache.ibatis.annotations.*;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import pojo.Address;
import pojo.User;

import java.util.List;

public interface UserMapper {
    @Update("update user set name=#{name},password=#{password},phone=#{phone},email=#{email} where id=#{id}")
    public int updUser(@Param("name") String name,
                       @Param("password") String password,
                       @Param("phone") String phone,
                       @Param("email") String email,
                       @Param("id") int id);

    @Select("select * from user where name = #{name}")
    public User selectUser(@Param("name") String name);

    @Insert("insert into user (name, password, Phone, email) values (#{name},#{password},#{phone},#{email})")
    public int insertUser(@Param("name") String name,
                          @Param("password") String password,
                          @Param("phone") String phone,
                          @Param("email") String email);

    @Insert("insert into address (address, uid, name, phone) values (#{address},#{uid},#{name},#{phone})")
    public int saveAddress(@Param("address") String address,
                           @Param("uid") int uid,
                           @Param("name") String name,
                           @Param("phone") String phone);

    // 查询密码
    @Select("select password from user where name=#{name} and answer=#{answer}")
    public User queryPwd(@Param("name") String name,
                         @Param("answer") String answer);

    // 默认查询地址
    @Select("select * from address where uid=#{uid}")
    public List<Address> address(@Param("uid") int uid);

    // 删除地址
    @Delete("delete from address where id=#{id}")
    public int delAddress(@Param("id") int id);

    // 新增问题
    @Insert("update user set problem=#{problem},answer=#{answer}  where id=#{id}")
    public int saveQ(@Param("problem") String problem,
                     @Param("answer") String answer,
                     @Param("id") int id);
}
