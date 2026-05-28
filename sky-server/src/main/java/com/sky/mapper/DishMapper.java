package com.sky.mapper;

import com.sky.annotation.AopAnnotation;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    //插入菜品
    @AopAnnotation(OperationType.INSERT)
    @Options(useGeneratedKeys = true , keyColumn = "id", keyProperty = "id")
    @Insert("insert into dish(name, category_id, price, image, description, status, create_time," +
            " update_time, create_user, update_user) values(#{name},#{categoryId},#{price},#{image},#{description},#{status}," +
            "#{createTime},#{updateTime},#{createUser},#{updateUser})")
    public void insert(Dish dish);

    //分页查询（包括口味）
    public List<DishVO> selectByPage(DishPageQueryDTO dishPageQueryDTO);


    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    @Select("select dish.*,category.name as categoryName from dish,category where dish.category_id=category.id and dish.id=#{id}")
    public DishVO getByIdWithFlavor(Long id);

    List<Dish> list(Dish dish);

    public void update(DishDTO dishDTO);

    //根据菜品分类id查询菜品
    @Select("select * from dish where category_id=#{categoryId}")
    public List<DishVO> listByCategoryId(Long categoryId);


}
