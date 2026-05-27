package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import java.util.List;

public interface SetmealService {

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);


    //分页查询
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    //插入数据
    void insert(SetmealDTO setmealDTO);

    //显示回显
    SetmealVO getById(Long id);

    //更新数据
    void update(SetmealDTO setmealDTO);

    //更新状态
    void updateStatus(Integer status, Long id);

    //批量删除
    void delete(List<Long> ids);

}
