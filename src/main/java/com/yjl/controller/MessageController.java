package com.yjl.controller;

import com.alibaba.fastjson.JSONObject;
import com.yjl.base.BaseController;
import com.yjl.pojo.Message;
import com.yjl.service.MessageService;
import com.yjl.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author yjl
 * @create 2020-07-16-18:29
 **/
@Controller
@RequestMapping("/message")
public class MessageController  extends BaseController {
    @Autowired
    private MessageService messageService;

    /**
     * 分页查询全部留言列表
     * @param message
     * @param model
     * @return
     */
    @RequestMapping("/findBySql")
    public String findBySql(Message message, Model model){
        String sql = "select * from message where 1=1 ";
        if(!isEmpty(message.getName())){
            sql += " and name like '%"+message.getName()+"%'";
        }
        sql += " order by id desc";
        Pager<Message> pagers = messageService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",message);
        return "message/message";
    }

    /**
     * 删除留言
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        messageService.deleteById(id);
        return "redirect:/message/findBySql";
    }

    /**
     * 用户发表留言页面
     * @return
     */
    @RequestMapping("/add")
    public String add(){
        return "message/add";
    }

    /**
     * 发表留言
     * @param message
     * @return
     */
    @RequestMapping("/exAdd")
    @ResponseBody
    public String exAdd(Message message){
        messageService.insert(message);
        JSONObject js = new JSONObject();
        js.put("message","添加成功");
        return js.toString();
    }
}
