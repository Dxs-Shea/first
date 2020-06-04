package com.dxs.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.dxs.pojo.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService extends IService<Users> {
    int queryRowCount();
    List<Users> selectPageBySql(Integer currIndex,Integer pageSize);

//    List<Users> selectByMyWrapper(Map map, Wrapper<Users> userWrapper);
}
