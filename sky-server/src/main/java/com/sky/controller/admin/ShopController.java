package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags="管理员商店状态相关操作")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @PutMapping("/{status}")
    @ApiOperation("更改商店状态")
    public Result updateStatus(@PathVariable Integer status){
        shopService.updateStatus(status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("获取商品状态")
    public Result<Integer>gainStatus(){
        return Result.success(shopService.gainStatus());
    }
}
