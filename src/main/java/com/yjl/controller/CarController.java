package com.yjl.controller;

import com.alibaba.fastjson.JSONObject;

import com.yjl.pojo.Car;
import com.yjl.pojo.Item;
import com.yjl.service.CarService;
import com.yjl.service.ItemService;
import com.yjl.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 购物车
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private ItemService itemService;

    /**
     * 添加到购物车中
     * @param car
     * @param request
     * @return
     */
    @RequestMapping("/exAdd")
    @ResponseBody
    public String exAdd(Car car, HttpServletRequest request){
        JSONObject js = new JSONObject();
        //从session中获取用户id判断用户是否登录
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        //如果用户没有登录跳转
        if(attribute==null){
            js.put(Consts.RES,0);
            return js.toJSONString();
        }
        //如果用户登录则
        //保存到购物车
        Integer userId = Integer.valueOf(attribute.toString());
        car.setUserId(userId);
        Item item = itemService.load(car.getItemId());
        String price = item.getPrice();
        Double valueOf = Double.valueOf(price);
        car.setPrice(valueOf);
        //判断当前商品添加购物车是否有折扣
        if(item.getZk()!=null){
            valueOf = valueOf*item.getZk()/10;
            BigDecimal bg = new BigDecimal(valueOf).setScale(2, RoundingMode.UP);
            car.setPrice(bg.doubleValue());
            valueOf = bg.doubleValue();
        }
        Integer num = car.getNum();
        Double t = valueOf*num;

        BigDecimal bg = new BigDecimal(t).setScale(2, RoundingMode.UP);
        double doubleValue = bg.doubleValue();
        car.setTotal(doubleValue+"");
        carService.insert(car);
        js.put(Consts.RES,1);
        return js.toJSONString();
    }

    /**
     * 转向到用户购物车页面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model,HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        //用户没有登录跳转到登录页面
        if(attribute==null){
            return "redirect:/login/toLogin";
        }
        Integer userId = Integer.valueOf(attribute.toString());
        String sql = "select * from car where user_id="+userId+" order by id desc";
        //把查询当前用户购物车的结果保存到list中
        List<Car> list = carService.listBySqlReturnEntity(sql);
        model.addAttribute("list",list);
        return "car/car";
    }

    /**
     * 删除用户购物车
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        //根据购物车id删除当前购物车
        carService.deleteById(id);
        return "success";
    }
}
