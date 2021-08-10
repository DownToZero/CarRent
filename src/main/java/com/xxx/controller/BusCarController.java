package com.xxx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.bean.BusCar;
import com.xxx.query.BusCarQuery;
import com.xxx.query.CarQuery;
import com.xxx.service.BusCarService;
import com.xxx.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bus_car")
public class BusCarController extends BaseController {
    @Autowired
    private BusCarService busCarService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> sayList(BusCarQuery query) {
        //实例化Map
        Map<String, Object> map = new HashMap<>();
        //开启分页单位
        PageHelper.startPage(query.getPage(), query.getLimit());
        //调用方法查询所有的信息
        List<BusCar> slist = busCarService.queryBusCarByParam(query);
        System.out.println(slist);
        //开始分页
        PageInfo<BusCar> plist = new PageInfo<BusCar>(slist);
        System.out.println(plist);
        //准备数据
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", plist.getTotal());
        map.put("data", plist.getList());
        //返回目标map
        return map;
    }

    @RequestMapping("index")
    public String index() {
        return "busCar/bus_car";
    }

    @RequestMapping("addOrUpdateBusCarPage")
    public String addOrUpdate(String busCarNumber, Model model) {
        //判断
        if (busCarNumber != null) {
            //查询对象
            BusCar busCar = busCarService.selectByPrimaryKey(busCarNumber);
            //存储
            model.addAttribute("busCar", busCar);
            System.out.println(model);
        }
        return "busCar/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(HttpServletRequest req, BusCar busCar) {
        //指定分配人
//        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);
        //查询用户信息
//        String trueName = userService.selectByPrimaryKey(userId).getTrueName();
        //赋值
//        saleChance.setCreateMan(trueName);
        //调佣方法添加
        busCarService.save(busCar);
        //返回目标对象
        return success("车辆信息添加成功了");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(BusCar busCar) {
        //调用方法修改
        busCarService.update(busCar);
        //提示一下信息
        return success("车辆信息更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo del(String[] ids) {
        //调用方法修改
        busCarService.removeIds(ids);
        //提示一下信息
        return success("车辆信息批量删除成功");
    }

    @RequestMapping("cars")
    @ResponseBody
    public List<Map<String, Object>> sayListSales(){
        //调用方法查询所有的销售
        List<Map<String, Object>> list = busCarService.findAllcars();
        //list-json
        return list;
    }

    /**
     * 跳转汽车出租页面
     * @return
     */
    @RequestMapping("/info")
    public String toIndex(){
        return "carInfo/carInfo";
    }

    /**
     * 获取汽车数据，渲染汽车出租表格
     * @param carQuery
     * @return
     */
    @RequestMapping("/infoList")
    @ResponseBody
    public Map<String,Object> queryLogLoginByParams(CarQuery carQuery){
        System.out.println("fmnsydfhjbsuufjd");
        return busCarService.queryByParamsForTable(carQuery);
    }

    @RequestMapping("/reset")
    @ResponseBody
    public ResultInfo reset(String busCarNumber) {
        //调用方法修改
        BusCar busCar = busCarService.selectByPrimaryKey(busCarNumber);

            busCarService.reset(busCar);
            //提示一下信息
            return success("车辆归还成功");

    }
}
