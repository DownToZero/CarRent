package com.xxx.service;

import com.xxx.base.BaseService;
import com.xxx.bean.BusCar;
import com.xxx.mapper.BusCarMapper;
import com.xxx.query.BusCarQuery;
import com.xxx.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BusCarService extends BaseService<BusCar,String> {

    @Autowired(required = false)
    private BusCarMapper busCarMapper;

    public List<BusCar> queryBusCarByParam(BusCarQuery query){
        return  busCarMapper.selectParams(query);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(BusCar busCar){
        //1.参数校验
        checkParams(busCar.getCarnumber(),busCar.getCartype(),busCar.getRentprice(),busCar.getDeposit());
        // 2.设置相关参数默认值
        busCar.setIsrenting(0);//0-未分配，1-已分配
        busCar.setCreatetime(new Date());

        //3.执行添加 判断结果
        AssertUtil.isTrue(busCarMapper.insertSelective(busCar)<1,"添加失败了");
    }

    private void checkParams(String carnumber,String cartype,Double rentprice,Double deposit) {
        AssertUtil.isTrue(StringUtils.isBlank(carnumber),"车牌号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(cartype),"车辆类型不能为空");
        AssertUtil.isTrue(rentprice==null,"出租价格不能为空");
        AssertUtil.isTrue(deposit==null,"出租押金不能为空");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(BusCar busCar){
        BusCar temp = busCarMapper.selectByPrimaryKey(busCar.getCarnumber());
        //判断
        System.out.println(temp);
        AssertUtil.isTrue(null==temp,"待修改记录不存在");
        //校验参数
        checkParams(busCar.getCarnumber(),busCar.getCartype(),busCar.getRentprice(),busCar.getDeposit());
        //3.执行更新 判断结果

        AssertUtil.isTrue(busCarMapper.updateByPrimaryKeySelective(busCar)<1,"修改失败了");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeIds(String [] ids){
        //验证
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择删除数据");
        //删除是否成功
        AssertUtil.isTrue(busCarMapper.deleteBatch(ids)<1,"批量删除失败了");
    }

    public List<Map<String, Object>> findAllcars() {
        return busCarMapper.selectAllCars();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void reset(BusCar busCar){
        if (busCar.getIsrenting() == 1) {
            busCarMapper.reset(busCar);
            busCarMapper.reset1(busCar);
        }else {
            AssertUtil.isTrue(busCar.getIsrenting() == 0,"车辆暂未出租！");
        }
    }
}
