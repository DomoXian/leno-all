package com.leno.controller;

import com.leno.base.norm.Auth;
import com.leno.base.norm.RestResult;
import com.leno.domain.DO.UserDO;
import com.leno.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>TODO</p>
 *
 * @author: XianGuo
 * @date: 2018年02月26日
 */
@RestController
public class TestController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/test")
    @Auth
    public Object test() {
        return "测试";
    }

    @GetMapping("/addUser.json")
    public Object testRedis() {
        UserDO userDO = new UserDO();
        userDO.setName("王二");
        userDO.setSex("男");
        userDO.setUserId(1);
        redisService.setValue("user", userDO);
        return RestResult.getSuccessResult(userDO);
    }

    @GetMapping("/getUser.json")
    public Object getUser() {
        return RestResult.getSuccessResult(redisService.getValue("user", UserDO.class));
    }


    @GetMapping("/addUserList.json")
    public Object addUserList() {

        UserDO userDO = new UserDO();
        userDO.setName("王二");
        userDO.setSex("男");
        userDO.setUserId(1);

        UserDO userDO1 = new UserDO();
        userDO1.setName("王三");
        userDO1.setSex("男");
        userDO1.setUserId(2);

        UserDO userDO2 = new UserDO();
        userDO2.setName("王四");
        userDO2.setSex("男");
        userDO2.setUserId(3);

        UserDO userDO3 = new UserDO();
        userDO3.setName("王五");
        userDO3.setSex("男");
        userDO3.setUserId(4);

        HashMap<Integer, UserDO> map = new HashMap<>(4);
        map.put(userDO.getUserId(), userDO);
        map.put(userDO1.getUserId(), userDO1);
        map.put(userDO2.getUserId(), userDO2);
        map.put(userDO3.getUserId(), userDO3);

        redisService.putHashAll("userList", map);
        return RestResult.getSuccessResult(map);
    }

    @GetMapping("/getUserList.json")
    public Object getUserList(){
        return null;
    }
}
