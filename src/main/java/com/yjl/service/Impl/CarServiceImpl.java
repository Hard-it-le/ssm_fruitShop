package com.yjl.service.Impl;

import com.yjl.base.BaseDao;
import com.yjl.base.BaseServiceImpl;
import com.yjl.mapper.CarMapper;
import com.yjl.pojo.Car;
import com.yjl.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends BaseServiceImpl<Car> implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public BaseDao<Car> getBaseDao() {
        return carMapper;
    }
}
