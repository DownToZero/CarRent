package com.xxx.mapper;

import com.xxx.base.BaseMapper;
import com.xxx.bean.BusCustomer;
import com.xxx.query.BusCustomerQuery;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface BusCustomerMapper  extends BaseMapper<BusCustomer,Integer> {
    int deleteByPrimaryKey(String identity);

    int insert(BusCustomer record);

    BusCustomer selectByPrimaryKey(String identity);

    int updateByPrimaryKey(BusCustomer record);

//    查询所有
   List<BusCustomer> selectOneByOne(BusCustomerQuery busCustomerQuery);
//    添加单条记录
//    int insetOne(BusCustomer busCustomer);

    public Integer deleteBatchs(String[] ids) throws DataAccessException;

    List<Map<String,Object>> findAllidentity();
}