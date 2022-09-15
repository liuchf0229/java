package com.liuchf.controller;

import com.liuchf.GirlRepository;
import com.liuchf.GirlService;
import com.liuchf.entity.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    // 查询所有
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        return girlRepository.findAll();
    }

    // 通过年龄查询
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    // 通过id查询
    @GetMapping(value = "/girls/{id}")
    public Girl getFindOne(@PathVariable("id") Integer id) {
        Optional<Girl>  a =  girlRepository.findById(id);
        if(a.isPresent()){
            return a.get();
        }
        return new Girl();
    }

    // 添加
    @PostMapping(value = "/girls")
    public Girl girlAdd(
            @RequestParam("cupSize") String cupSize,
            @RequestParam("age") Integer age
    ) {
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);

        return girlRepository.save(girl);
    }

    // 插入两个
    @PostMapping(value = "girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

    // 更新
    @PutMapping(value = "/girls/{id}")
    public Girl updateGirl(
            @PathVariable("id") Integer id,
            @RequestParam("cupSize") String cupSize,
            @RequestParam("age") Integer age
    ) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);

        return girlRepository.save(girl);
    }

    // 删除
    @DeleteMapping(value = "/girls/{id}")
    public void deleteGirl(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

}
