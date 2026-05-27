package com.sky.mapper;

import com.sky.entity.SetmealDish;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

   //插入与套餐关联的菜品数据
    void insertBatch(List<SetmealDish> setmealDishes);

    //显示回显
    @Select("select * from setmeal_dish where setmeal_id=#{id}")
    List<SetmealDish> getById(Long id);

    //删除套餐中所有的商品
    @Delete("delete from setmeal_dish where setmeal_id=#{id}")
    void deleteBySetmealId(Long id);

    //添加套餐中菜品
    void insert(List<SetmealDish> setmealDishes);

    //删除套餐跟随删除
    void deleteBySetmealIds(List<Long> ids);
}
