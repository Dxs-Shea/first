package com.dxs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dxs.pojo.Users;
import com.dxs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/selectById/{id}")
    public String selectById(@PathVariable("id") Integer id){

       // logger.info("ok");
        Users user = userService.selectById(id);
        String s = JSON.toJSONString(user);
        return s;
    }

   @GetMapping("/selectList")
    public String selectList(){

        // logger.info("ok");
        List<Users> users = userService.selectList(null);
        String s = JSON.toJSONString(users);
        return s;
    }


    @PostMapping("/selectPage")
    public String selectPage(@RequestBody String jsonStr){

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Integer startIndex = jsonObject.getInteger("startIndex");
        Integer pageSize = jsonObject.getInteger("pageSize");

        EntityWrapper<Users> entityWrapper = new EntityWrapper<>();
        entityWrapper.gt("age",20);

        Page<Users> page = new Page<>(startIndex, pageSize);
        Page<Users> selectPage = userService.selectPage(page,entityWrapper);

        String s = JSON.toJSONString(selectPage);
        return s;
    }

    @PostMapping("/selectPageBySql")
    public JSONObject selectPageBySql(@RequestBody JSONObject jsonObject){
        try {
            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");

            startPage = startPage==null?1:startPage;
            pageSize = pageSize==null?5:pageSize;

            //获取总数据大小
            int total = userService.queryRowCount();
            int currIndex = (startPage - 1) * pageSize;
            //获取当前页数据
            List<Users> users = userService.selectPageBySql(currIndex, pageSize);

            JSONObject ret = new JSONObject();
            ret.put("total", total);
            ret.put("users", users);
            return ret;
        } catch(Exception e){
            logger.error(e.getMessage(), e);
        }
        return null;
    }

   /* @PostMapping("/selectByMyWrapper")
    public String selectByMyWrapper(Integer startIndex,Integer pageSize){
        EntityWrapper<Users> wrapper = new EntityWrapper<>();
        wrapper.gt("sex", "女");

        //Page<Users> page = new Page<>(startIndex,pageSize);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex",startIndex);
        map.put("pageSize",pageSize);
        List<Users> users = userService.selectByMyWrapper(map, wrapper);

        String s = JSON.toJSONString(users);
        return s;
    }
*/
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Integer id){

        // logger.info("ok");
        userService.deleteById(id);
        List<Users> users = userService.selectList(null);
        String s = JSON.toJSONString(users);
        return s;
    }

    @RequestMapping("/insertUser")
    public String insertUser(@RequestParam("name") String name,
                             @RequestParam(value = "age",required = false,defaultValue = "0")Integer age,
                             @RequestParam(value = "sex",required = false,defaultValue = "男")String sex){
        Users user = new Users();
        user.setAge(age);
        user.setSex(sex);
        user.setName(name);
        userService.insert(user);
        List<Users> users = userService.selectList(null);
        String s = JSON.toJSONString(users);
        return s;

    }

    @RequestMapping("/updateUser/{id}")
    public String updateUser(@RequestParam(value = "name",required = false) String name,
                             @RequestParam(value = "age",required = false)Integer age,
                             @RequestParam(value = "sex",required = false)String sex,
                             @PathVariable("id") Integer id) {
        Users user = userService.selectById(id);
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);

        userService.updateById(user);

        List<Users> users = userService.selectList(null);
        String s = JSON.toJSONString(users);
        return s;
    }


}
