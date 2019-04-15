package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.User;

public interface LoginMapper {
    @Select("select * from user where name=#{name} and password=#{password}")
    public User selectLogin(@Param("name") String name,
                            @Param("password") String password);
}
