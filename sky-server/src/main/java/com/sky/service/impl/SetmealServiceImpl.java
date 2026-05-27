package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 套餐业务实现
 */
@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private DishMapper dishMapper;

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list = setmealMapper.list(setmeal);
        return list;
    }

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }

    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
      PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
      List<SetmealVO> list = setmealMapper.pageQuery(setmealPageQueryDTO);
      Page<SetmealVO> page = (Page<SetmealVO>) list;
      return new PageResult(page.getTotal(),page.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(SetmealDTO setmealDTO) {
     setmealMapper.insert(setmealDTO);
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(e->{
            e.setSetmealId(setmealDTO.getId());
            log.info("oioioioioioio{}",e);
        });
        setmealDishMapper.insertBatch(setmealDishes);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SetmealVO getById(Long id) {
        SetmealVO byId = setmealMapper.getById(id);
        List<SetmealDish> byId1 = setmealDishMapper.getById(id);
        byId.setSetmealDishes(byId1);
        return byId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(SetmealDTO setmealDTO) {
         setmealMapper.update(setmealDTO);
          setmealDishMapper.deleteBySetmealId(setmealDTO.getId());
          List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
          setmealDishes.forEach(e->{
              e.setSetmealId(setmealDTO.getId());
          });
          setmealDishMapper.insert(setmealDishes);
    }

    @Override
    public void updateStatus(Integer status, Long id) {
        setmealMapper.updateStatus(status,id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Long> ids) {
         setmealMapper.delete(ids);
         setmealDishMapper.deleteBySetmealIds(ids);
    }
}
