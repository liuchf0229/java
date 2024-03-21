package com.example.usermanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.usermanager.entity.User;
import com.example.usermanager.entity.UserSearch;
import com.example.usermanager.mapper.UserMapper;
import com.example.usermanager.util.JWTUtils;
import com.example.usermanager.util.ResultUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qinkui
 * @since 2024-03-11
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserMapper userMapper;

   /**
    * 查询所有未被删除的用户信息
    * */
    @GetMapping("/getAllUsers")
    public Object getAllUsers(){

        List<User> users = userMapper.selectList(null);
        return ResultUtil.isSuccess(users);
    }

    /**添加用户*/
    @PostMapping("/addUser")
    public Object addUser(@RequestBody User user){
        int i =userMapper.insert(user);
        if(i==1){
            return ResultUtil.isSuccess("添加成功",null);
        }else{
            return ResultUtil.isFail(500,"添加失败");
        }
    }
    /**更新用户**/
    @PostMapping("/updateUser")
    public Object updateUser(@RequestBody User user){
        int i =userMapper.updateById(user);
        if(i==1){
            return ResultUtil.isSuccess("更新成功",null);
        }else{
            return ResultUtil.isFail(500,"更新失败");
        }
    }
    /**删除用户**/
    @PostMapping("/delUser")
    public Object delUser(Integer userId){
        int i =userMapper.deleteById(userId);
        if(i==1){
            return ResultUtil.isSuccess("删除成功",null);
        }else{
            return ResultUtil.isFail(500,"删除失败");
        }
    }

    /**多条件分页查询*/
    @RequestMapping("/getAllUserByCon")
    public Object getAllUserByCon(@RequestBody UserSearch userSearch){
        //分页对象
        Page<User> page=new Page<>(userSearch.getPageNo(),userSearch.getPageSize());
        //条件构造器
        QueryWrapper<User> wrapper=new QueryWrapper<>();

        if(userSearch.getUserName()!=null&&!userSearch.getUserName().equals("")){
            wrapper.like("username",userSearch.getUserName());
        }
        if(userSearch.getRoleId()!=null){
            wrapper.eq("roleId",userSearch.getRoleId());
        }

        List<User> users = userMapper.selectList(page, wrapper);//多条件分页后的数据
        System.out.println("总记录数："+page.getTotal());
        return ResultUtil.isSuccess(users);
    }

    /**
     * http://localhost:8080/user/login
     * 登录功能
     * {code:20000,msg:"操作成功",status:true,data:“token的值”}
     *
     * vue客户端发送json格式的多个参数时，服务端必须通过对象来接收参数
     */
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        QueryWrapper<User> wrapper=new QueryWrapper<User>();
        wrapper.eq("username",user.getUsername());
        User us = userMapper.selectOne(wrapper);//根据用户名获取用户信息
        if(us!=null){
            if(us.getPassword().equals(user.getPassword())){
//验证用户通过,此时应该生成token令牌返回给客户端
                String token = JWTUtils.getToken(us.getId(), us.getUsername());
                return ResultUtil.isSuccess(token);
            }else{
                return ResultUtil.isSuccess("密码不正确！",null);
            }
        }else{
            return ResultUtil.isSuccess("用户名不存在！",null);
        }
    }
    /**
     * http://localhost:8080/user/info
     * 获取用户信息
     * {code:20000,msg:"操作成功",status:true,data:“admin”}
     */
    @GetMapping("/info")
    public Object getInfo(String token){
        System.out.println("token="+token);
        //解析token,获取用户名
        Claims claims = JWTUtils.parseToken(token);
        String username =(String) claims.get("username");
        System.out.println("username="+username);
        Map<String,Object> map=new HashMap<>();
        map.put("name",username);
        map.put("avatar","http://localhost:8080/um/photo/gril.png");
        return ResultUtil.isSuccess(map);
    }
    /**
     * 退出功能
     */
    @PostMapping("/logout")
    public Object logout(String token){
        System.out.println("token="+token);
        return ResultUtil.isSuccess(null);
    }
}
