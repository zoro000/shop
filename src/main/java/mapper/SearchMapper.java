package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Product;

import java.util.List;

public interface SearchMapper {
    public List<Product> selectBySubTitle(@Param("subTitle") String subTitle);

    public List<Product> selectByCid(int cid);
}
