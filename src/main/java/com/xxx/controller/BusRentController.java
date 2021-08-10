package com.xxx.controller;

import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.bean.BusRent;
import com.xxx.query.BusRentQuery;
import com.xxx.service.BusCentService;
import com.xxx.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation. RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("bus_rent")
public class BusRentController extends BaseController{

    @Autowired(required = false)
    private BusCentService busCentService;

    @RequestMapping("index")
    public String index() {
        return "business/busrent";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> sayList(BusRentQuery query, Integer flag, HttpServletRequest req) {
        //返回目标map
        return busCentService.findall(query);
    }

    @RequestMapping("addOrUpdateBusrentPage")
    public String addOrUpdate(){
        System.out.println("进入添加页面");
        return "business/add_update";
    }

    @RequestMapping("select")
    public String select(String rentid, Model model){
        //设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        BusRent busRent = busCentService.selectByPrimaryKey(rentid);
        //存储

        String begindate = sdf.format(busRent.getBegindate());
        String returndate = sdf.format(busRent.getReturndate());

        model.addAttribute("busrent", busRent);
        model.addAttribute("begindate", begindate);
        model.addAttribute("returndate", returndate);

        System.out.println("进入查看页面");
        return "business/select";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(HttpServletRequest req, BusRent busRent,Integer time) {
        System.out.println(time);
        System.out.println("进入添加事件");
        busCentService.save(busRent,time,req);
        return success("出租单添加成功了");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo dels(String[] ids) {
        //调用方法修改
        busCentService.removeIds(ids);
        //提示一下信息
        return success("出租单删除成功");
    }
}
