package org.csu.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.management.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
