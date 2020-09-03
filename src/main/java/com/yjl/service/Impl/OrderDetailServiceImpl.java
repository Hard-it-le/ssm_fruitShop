package com.yjl.service.Impl;

import com.yjl.base.BaseDao;
import com.yjl.base.BaseServiceImpl;
import com.yjl.mapper.OrderDetailMapper;
import com.yjl.pojo.OrderDetail;
import com.yjl.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public BaseDao<OrderDetail> getBaseDao() {
        return orderDetailMapper;
    }
}
