package com.dxs.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dxs.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


//@Component
public interface UserMapper extends BaseMapper<Users> {

   List<Users> selectPageBySql(Map<String,Integer>map);
   int queryRowCount();


//    List<Users> selectByMyWrapper(Map map,Wrapper<Users> userWrapper);
}

