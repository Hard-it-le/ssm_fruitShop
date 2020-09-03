package com.yjl.controller;

import com.yjl.base.BaseController;
import com.yjl.pojo.News;
import com.yjl.service.NewsService;
import com.yjl.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author yjl
 * @create 2020-07-16-17:51
 **/
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
    @Autowired
    private NewsService newsService;

    /**
     * 分页查询公告列表
     * @param news
     * @param model
     * @return
     */
    @RequestMapping("/findBySql")
    public String findBySql(News news, Model model){
        String sql = "select * from news where 1=1 ";
        if(!isEmpty(news.getName())){
            sql += " and name like '%"+news.getName()+"%'";
        }
        sql += " order by id desc";
        Pager<News> pagers = newsService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",news);
        return "news/news";
    }

    /**
     * 跳转到添加公告页面
     * @return
     */
    @RequestMapping("/add")
    public String add(){
        return "news/add";
    }

    /**
     * 添加公告
     * @param news
     * @return
     */
    @RequestMapping("/exAdd")
    public String exAdd(News news){
        news.setAddTime(new Date());
        newsService.insert(news);
        return "redirect:/news/findBySql";
    }

    /**
     * 跳转到修改公告页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public String update(Integer id,Model model){
        News obj = newsService.load(id);
        model.addAttribute("obj",obj);
        return "news/update";
    }

    /**
     * 修改公告
     * @param news
     * @return
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(News news){
        newsService.updateById(news);
        return "redirect:/news/findBySql";
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        newsService.deleteById(id);
        return "redirect:/news/findBySql";
    }

    /**
     * 前端公告列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        Pager<News> pagers = newsService.findByEntity(new News());
        model.addAttribute("pagers",pagers);
        return "news/list";
    }

    /**
     * 公告详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/view")
    public String view(Integer id,Model model){
        News obj = newsService.load(id);
        model.addAttribute("obj",obj);
        return "news/view";
    }
}

