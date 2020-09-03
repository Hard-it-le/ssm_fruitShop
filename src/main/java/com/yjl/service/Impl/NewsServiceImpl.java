package com.yjl.service.Impl;

import com.yjl.base.BaseDao;
import com.yjl.base.BaseServiceImpl;
import com.yjl.mapper.NewsMapper;
import com.yjl.pojo.News;
import com.yjl.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public BaseDao<News> getBaseDao() {
        return newsMapper;
    }
}
