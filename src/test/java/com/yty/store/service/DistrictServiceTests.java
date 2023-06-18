package com.yty.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Yang
 * @create 2023-06-01 18:25
 * @Description: 区域业务层 测试
 */
@SpringBootTest
public class DistrictServiceTests {
    @Resource
    private DistrictService districtService;
    @Test
    void getByParent(){
        districtService.getByParent("86");
    }
}
