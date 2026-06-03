package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;

    //处理订单超时
    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 0/1 * * * ?")
    public void dealWithTimeOutOrder(){
        List<Orders> orders = orderMapper.getByStatusAndOrderTimeLT(1, LocalDateTime.now().plusMinutes(-15));
        if(orders!=null){
            orders.forEach(e->{
                e.setCancelTime(LocalDateTime.now());
                e.setCancelReason("订单超时取消");
                e.setStatus(6);
                orderMapper.update(e);
            });

        }
    }

    //处理一直处理派送中的订单
    @Scheduled(cron = "0 0 1 * * ?")
    public void dealWithSendOrder(){
        List<Orders> ordersByStatus = orderMapper.getOrdersByStatus(4);
        if(ordersByStatus!=null&&ordersByStatus.size()>0){
            ordersByStatus.forEach(e->{
                e.setDeliveryTime(LocalDateTime.now());
                e.setStatus(5);
                orderMapper.update(e);
            });
        }
    }

}
