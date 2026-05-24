package com.sky.mapper;

import com.sky.annotation.AopAnnotation;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishFlavorMapper {


    public void insert(List<DishFlavor>flavors);

    @Delete("delete from dish_flavor where dish_id=#{dishId}")
    public void deleteByDishId(Long dishId);

    @Select("select * from dish_flavor where dish_id=#{dishId}")
    public List<DishFlavor> getByDishId(Long dishId);


    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishIds(Long dishId);


}
