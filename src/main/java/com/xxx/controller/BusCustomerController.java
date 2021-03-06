package com.xxx.controller;
import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.bean.BusCustomer;
import com.xxx.mapper.BusCustomerMapper;
import com.xxx.query.BusCustomerQuery;
import com.xxx.service.BusCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("Bus_Customer")
public class BusCustomerController extends BaseController {

    @Autowired
    private BusCustomerService busCustomerService;
    @Autowired(required = false)
    private BusCustomerMapper busCustomerMapper;

    /**
     * 主页跳转
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "BusCustomer/Bus_customer";
    }

    /**
     * 跳转添加页面
     * @param identity
     * @param model
     * @return
     */
    @RequestMapping("addOrBusCustomer")
    public String addBustomer (String identity, Model model){
        if(identity!=null){
            BusCustomer busCustomer = busCustomerMapper.selectByPrimaryKey(identity);
                if(busCustomer.getSex().equals("0")){
                    busCustomer.setSex("女");
                }
                if(busCustomer.getSex().equals("1")){
                    busCustomer.setSex("男");
                }
            model.addAttribute("busCustomer",busCustomer);
        }
        return "BusCustomer/add_BusCustomer";
    }
    /**
     * 查询所有
     * @param query
     * @return
     */
    @RequestMapping("select")
    @ResponseBody
    public Map<String, Object> selectAll(BusCustomerQuery query){
        return busCustomerService.selectAll(query);
    }

    /**
     * 添加一条记录
     * @param busCustomer
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public ResultInfo insertOne(BusCustomer busCustomer) throws Exception{
        System.out.println(success().getCode()+"......");
        busCustomerService.insetOne(busCustomer);
        System.out.println(success().getCode()+"状态");
        return  success("添加成功",200);
    }
//    if()

    /**
     * 单删或批量删除
     * @param ids
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(String[] ids){
        busCustomerService.delete(ids);
        return  success("删除成功");
    }

    /**
     * 修改数据
     * @param request
     * @param busCustomer
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(HttpServletRequest request, BusCustomer busCustomer){
        busCustomerService.update(busCustomer);
        return  success("修改成功");
    }
}
