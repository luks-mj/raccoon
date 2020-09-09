package com.mujun.mng.controller;


import com.mujun.mng.commons.model.RestResult;
import com.mujun.mng.model.User;
import com.mujun.mng.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value="UserInfoController",tags={"UserInfoController"},description ="用户信息查询控制类")
@RestController
public class UserInfoController {


    @Autowired
    private UserService userService;

    @ApiOperation(value="查询用户",notes="用户信息查询")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public RestResult queryUserInfo(){
        RestResult queryResult = new RestResult();
        List<Map<String, Object>> list = userService.queryUserInfo();
        queryResult.setData(list);
        queryResult.setCode(200);
        return  queryResult;
    }


    @ApiOperation(value="新增用户",notes="用户信息查询")
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public RestResult insertUser(){
        RestResult queryResult = new RestResult();
        User user = new User();
        user.setAge(18);
        user.setName("凯撒");
        Integer result = userService.insert(user);
        queryResult.setData(result);
        queryResult.setCode(200);
        return  queryResult;
    }
}
