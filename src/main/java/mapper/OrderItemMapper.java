package mapper;

import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {
    public int selectCountItem(@Param("uid") int uid);
}
