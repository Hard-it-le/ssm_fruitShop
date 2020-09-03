package com.yjl.controller;

import com.yjl.base.BaseController;
import com.yjl.pojo.OrderDetail;
import com.yjl.service.OrderDetailService;
import com.yjl.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yjl
 * @create 2020-07-17-10:33
 * 订单详情控制器
 **/
@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController  extends BaseController {

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 分页查询
     * @param orderDetail
     * @param model
     * @return
     */
    @RequestMapping("/ulist")
    public String ulist(OrderDetail orderDetail, Model model){
        //分页查询
        String sql = "select * from order_detail  where order_id="+orderDetail.getOrderId();
        Pager<OrderDetail> pagers = orderDetailService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",orderDetail);
        return "orderDetail/ulist";
    }

}
