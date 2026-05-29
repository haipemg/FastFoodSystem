package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("  insert into orders (number, status, user_id, address_book_id, order_time, checkout_time, pay_method, pay_status,\n" +
            "                            amount, remark, phone, address, consignee, estimated_delivery_time, delivery_status,\n" +
            "                            pack_amount, tableware_number, tableware_status)\n" +
            "        values\n" +
            "               (#{number}, #{status}, #{userId}, #{addressBookId}, #{orderTime}, #{checkoutTime}, #{payMethod},\n" +
            "                #{payStatus},#{amount}, #{remark}, #{phone}, #{address}, #{consignee}, #{estimatedDeliveryTime},\n" +
            "                #{deliveryStatus},#{packAmount}, #{tablewareNumber}, #{tablewareStatus})")
            public void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);


}
