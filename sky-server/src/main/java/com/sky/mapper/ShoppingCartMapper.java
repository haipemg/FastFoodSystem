package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    //查询购物车是否存在
    Integer count(ShoppingCart shoppingCart);

    //修改购物车更新数量
    void updateNumberById(ShoppingCart shoppingCart);

    //添加购物车
    @Insert("insert into shopping_cart (name, image, dish_id, setmeal_id, dish_flavor, number, amount, create_time, user_id) " +
            "values (#{name}, #{image}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{createTime}, #{userId})")
    void add(ShoppingCart shoppingCart);

    //查询购物车
    @Select("select * from shopping_cart where user_id=#{userId}")
    public List<ShoppingCart> list(Long userId);

    //查询购物车中某个商品数量
    Integer countNumber(ShoppingCart shoppingCart);

    //减少数量
    void subNumber(ShoppingCart shoppingCart);

    //直接删除购物车中某个商品
    void delete(ShoppingCart shoppingCart);

    //清空购物车
    @Delete("delete from shopping_cart where user_id=#{id}")
    void deleteAll(Long id);
}
