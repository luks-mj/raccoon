package com.mujun.mng.controller;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mujun.mng.commons.model.RestResult;
import com.mujun.mng.model.User;
import com.mujun.mng.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value="UserInfoController",tags={"UserInfoController"},description ="用户信息查询控制类")
@RestController
public class UserInfoController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);


    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/user/info")
    public User getCurrentHr(Authentication authentication) {
        return ((User) authentication.getPrincipal());
    }


    @ApiOperation(value="查询用户",notes="用户信息查询")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public RestResult queryUserInfo(){
        RestResult queryResult = new RestResult();
//        List<Map<String, Object>> list = userService.queryUserInfo();
        Long id1 = IdWorker.getId(new User());
        Long id2 = IdWorker.getId();
        logger.info("Id1生成值{}，id2生成主键值->:{}", id1, id2);
        queryResult.setInfo(id2);
        queryResult.setMeta(HttpStatus.OK.value(),"查询成功");
        return  queryResult;
    }


    @ApiOperation(value="新增用户",notes="用户信息查询")
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public RestResult insertUser(){
//        RestResult queryResult = new RestResult();
//        User user = new User();
//        user.setAge(18);
//        user.setName("凯撒");
//        Integer result = userService.insert(user);
//        queryResult.setInfo(result);
//        queryResult.setMeta(HttpStatus.ACCEPTED.value(),"");

        return  null;
    }
}
