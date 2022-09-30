package com.javaPractice.demo.mapper;

import com.javaPractice.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//step2 建议mapper接口，定义要操作数据库的动作
@Mapper
@Repository
public interface UserMapper {
//    @Select("SELECT * From user")
    List<User> findAll();

}
