package com.xxx.mapper;

import com.xxx.base.BaseMapper;
import com.xxx.bean.BusCar;
import com.xxx.query.BusCarQuery;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface BusCarMapper extends BaseMapper<BusCar,String> {
    public List<BusCar> selectParams(BusCarQuery query);
    @MapKey("")
    public List<Map<String,Object>> selectAllCars();
    public void reset(BusCar busCar);
    public void reset1(BusCar busCar);
}