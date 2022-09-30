package com.javaPractice.practice.service;

import com.javaPractice.practice.domain.Test;
import com.javaPractice.practice.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.selectByExample(null);
    }
}

