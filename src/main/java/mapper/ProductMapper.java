package mapper;

import pojo.Product;

import java.util.List;

public interface ProductMapper {
    //根据商品id查询商品信息
    public Product queryProduct(int id);
    //查询商品信息到主页
    public List<Product> queryAll();
    //查询商品及商品图片路径到首页
    public List<Product> queryProductAndPath();

}
