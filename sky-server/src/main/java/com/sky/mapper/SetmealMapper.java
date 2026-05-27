package com.sky.mapper;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * 动态条件查询套餐
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询菜品选项
     * @param setmealId
     * @return
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);

    //分页查询套餐
    public List<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    //插入套餐
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("insert into setmeal(category_id, name, price, status, description, image) " +
            "values(#{categoryId},#{name},#{price},#{status},#{description},#{image})")
    public void insert(SetmealDTO setmealDTO);

    //显示回显
    public SetmealVO getById(Long id);

    //更新套餐数据
    @Update("update setmeal set category_id=#{categoryId},name=#{name},price=#{price}" +
            ",image=#{image},description=#{description} where id=#{id}")
    public void update(SetmealDTO setmealDTO);

    //修改销售状态
    @Update("update setmeal set status=#{status} where id=#{id}")
    public void updateStatus(Integer status, Long id);

    //批量删除
    public void delete(List<Long> ids);
}
