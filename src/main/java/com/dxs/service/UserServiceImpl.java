package com.dxs.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dxs.dao.UserMapper;
import com.dxs.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,Users> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public int queryRowCount() {
        return userMapper.queryRowCount();
    }

    @Override
    public List<Users> selectPageBySql(Integer startIndex,Integer pageSize) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex",startIndex);
        map.put("pageSize",pageSize);
        return userMapper.selectPageBySql(map);
    }



  /*  public List<Users> selectByMyWrapper(Map map,Wrapper<Users> wrapper) {
        List<Users> users = userMapper.selectByMyWrapper(map,wrapper);
        return users;
    }
*/
}
