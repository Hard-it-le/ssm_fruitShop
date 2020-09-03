package com.yjl.service.Impl;

import com.yjl.base.BaseDao;
import com.yjl.base.BaseServiceImpl;
import com.yjl.mapper.ItemMapper;
import com.yjl.pojo.Item;
import com.yjl.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Override
    public BaseDao<Item> getBaseDao() {
        return itemMapper;
    }
}
