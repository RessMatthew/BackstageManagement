package org.csu.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Description 避免mybatis-plus而用于映射表orderstatus
 * @Date 2022/3/13 9:34 下午

 **/

@Data
@TableName("orderstatus")
public class OrderStatus {

    @TableId("orderid")
    private int orderid;
    private int linenum;
    private Date timestamp;
    private String status;
}
