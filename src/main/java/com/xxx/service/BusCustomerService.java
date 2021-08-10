package com.xxx.service;

import com.xxx.base.BaseService;
import com.xxx.bean.BusCustomer;
import com.xxx.mapper.BusCustomerMapper;
import com.xxx.query.BusCustomerQuery;
import com.xxx.utils.AssertUtil;
import com.xxx.utils.IdentityUtil;
import com.xxx.utils.PhoneUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusCustomerService extends BaseService<BusCustomer,Integer> {

    @Autowired(required = false)
    private BusCustomerMapper busCustomerMapper;

    /**
     * 查询所有数据或根据字段查
     * @param busCustomerQuery
     * @return
     */
    public Map<String,Object> selectAll(BusCustomerQuery busCustomerQuery){
//        实例化map
        Map<String,Object>  map=new HashMap<>();
//        初始化分页参数
        PageHelper.startPage(busCustomerQuery.getPage(),busCustomerQuery.getLimit());
//        将前台传入的sex字符转化为整形
        if(busCustomerQuery.getSex()!=null){
            if(busCustomerQuery.getSex().equals("男")){
                busCustomerQuery.setSex(String.valueOf(1));
            }
            if(busCustomerQuery.getSex().equals("女")){
                busCustomerQuery.setSex(String.valueOf(0));
            }
        }
//        查询参数,并用list集合接收
        List<BusCustomer> list= busCustomerMapper.selectOneByOne(busCustomerQuery);
//        将查出的sex值->int类型转为String
        if(list!=null){
            for (int i = 0; i <list.size(); i++) {
                BusCustomer busCustomer=list.get(i);
                if (busCustomer.getSex().equals("0")){
                    busCustomer.setSex("女");
                }
                else if(busCustomer.getSex().equals("1")){
                    busCustomer.setSex("男");
                }
            }
        }
//        开始分页
        PageInfo<BusCustomer> plist=new PageInfo<BusCustomer>(list);
        map.put("code",0);
        map.put("msg","");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        return map;
    }

    /**
     * 添加一条记录
     * @param busCustomer
     */
    public void insetOne(BusCustomer busCustomer){
//        判断信息格式是否符合规范
        checkFormat(busCustomer);
//        更改性别为字符
        BusCustomer change = change(busCustomer);
        change.setCreatetime(new Date());
        busCustomerMapper.insertSelective(change);
    }

    /**
     * 校验信息是否符合格式
     * @param busCustomer
     */
    public void checkFormat(BusCustomer busCustomer){
//        校验identity身份证号
        AssertUtil.isTrue(StringUtils.isBlank(busCustomer.getIdentity()),"身份证号码不能为空");
//        判断身份证格式是否正确
//        AssertUtil.isTrue(!(IdentityUtil.isMobile(busCustomer.getIdentity())),"身份证输入错误");
        System.out.println("身份证校验");
//        客户名不能为空
        AssertUtil.isTrue(busCustomer.getCustname()==null,"客户名不能为空");
//        性别不能为空
        AssertUtil.isTrue(busCustomer.getSex()==null,"性格不能为空");
//        电话不能为空
        AssertUtil.isTrue(StringUtils.isBlank(busCustomer.getPhone()),"电话不能为空");
//        判断电话号格式是否正确
        AssertUtil.isTrue(!(PhoneUtil.isMobile(busCustomer.getPhone())),"电话号输入错误");
//        AssertUtil.isTrue(!(PhoneUtil.isMobile(linkPhone)),"请输入合法的手机号");

        System.out.println("电话校验");
    }

    /**
     * 批量删除
     * @param id  identity值
     */
    public void  delete(String[] id){
        AssertUtil.isTrue(id==null,"未选择数据");
        busCustomerMapper.deleteBatchs(id);
    }

    /**
     * 修改数据
     * @param busCustomer
     */
    public void update(BusCustomer busCustomer){
//        判断信息格式是否符合规范
        checkFormat(busCustomer);
//        把性别字符更改为int类型
        BusCustomer change = change(busCustomer);
//        判断该身份证号码是否存在
        BusCustomer bus = busCustomerMapper.selectByPrimaryKey(change.getIdentity());
        AssertUtil.isTrue(bus==null,"修改失败");
//        更改数据
        AssertUtil.isTrue(busCustomerMapper.updateByPrimaryKeySelective(change)<1,"修改失败");
    }

    /**
     * 把性别字符更改为int类型
     * @param busCustomer
     * @return
     */
    public BusCustomer change(BusCustomer busCustomer){
        if(busCustomer.getSex().equals("男")){
            busCustomer.setSex(String.valueOf(1));
        }
        if(busCustomer.getSex().equals("女")){
            busCustomer.setSex(String.valueOf(0));
        }
        return  busCustomer;
    }
}
