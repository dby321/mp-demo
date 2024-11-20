package com.itheima.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author dongbinyu
 * @version 1.0
 * @project mp-demo
 * @description
 * @date 2024/11/20 22:32:28
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private  IUserService userService;



    @ApiOperation("保存用户信息")
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userFormDTO){
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        userService.save(user);
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/{id}")
    public void deleteUser(@ApiParam("用户id") @PathVariable Long id){
        userService.removeById(id);
    }

    @ApiOperation("根据ID查询用户信息")
    @GetMapping("/{id}")
    public UserVO queryUserById(@ApiParam("用户id") @PathVariable Long id){
        User user = userService.getById(id);
        return BeanUtil.copyProperties(user, UserVO.class);
    }



}
