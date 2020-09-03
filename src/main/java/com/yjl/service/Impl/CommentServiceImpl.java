package com.yjl.service.Impl;

import com.yjl.base.BaseDao;
import com.yjl.base.BaseServiceImpl;
import com.yjl.mapper.CommentMapper;
import com.yjl.pojo.Comment;
import com.yjl.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yu183
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseDao<Comment> getBaseDao() {
        return commentMapper;
    }
}
