package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.User;

import java.util.List;

public interface AdminMapper {
    @Select("select count(*) from user")
    public int userNum();

    // 查询订单数量
    @Select("select count(*) from `order`")
    public int orderNum();

    @Select("select * from user")
    public List<User> selectAll();

    @Select("select * from user where name like concat('%',concat(#{name},'%'))")
    public List<User> queryUser(@Param("name") String name);

    @Delete("delete from user where id = #{id} ")
    public int delUser(@Param("id") int id);



}
