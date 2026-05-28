package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminSetMealController")
@Api(tags = "管理端-套餐接口")
@RequestMapping("/admin/setmeal")
public class SetMealController {
    @Autowired
    private SetmealService setMealService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setMealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @CacheEvict(cacheNames = "setmeal",allEntries = true)
    @ApiOperation("插入套餐数据")
    @PostMapping
    public Result insert(@RequestBody SetmealDTO setmealDTO) {
        setMealService.insert(setmealDTO);
        return Result.success();
    }

    //显示回显
    @ApiOperation("回显套餐数据")
    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealVO setmealVO = setMealService.getById(id);
        return Result.success(setmealVO);
    }

    @CacheEvict(cacheNames = "setmeal",allEntries = true)
    @ApiOperation("更新套餐数据")
    @PutMapping
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        setMealService.update(setmealDTO);
        return Result.success();
    }

    @CacheEvict(cacheNames = "setmeal",allEntries = true)

    @ApiOperation("修改套餐销售状态")
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        setMealService.updateStatus(status, id);
        return Result.success();
    }

    @CacheEvict(cacheNames = "setmeal",allEntries = true)
    @ApiOperation("批量删除套餐")
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids) {
        setMealService.delete(ids);
        return Result.success();
    }
}
