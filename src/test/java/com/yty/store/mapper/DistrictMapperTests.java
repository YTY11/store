package com.yty.store.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Yang
 * @create 2023-05-31 21:57
 * @Description: DistrictMapper 测试
 */
@SpringBootTest
public class DistrictMapperTests {
    @Resource
    private DistrictMapper districtMapper;
    @Test
    void findByParent(){
        districtMapper.findByParent("110100");
    }
}
