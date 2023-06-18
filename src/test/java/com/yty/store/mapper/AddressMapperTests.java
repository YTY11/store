package com.yty.store.mapper;

import com.yty.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Yang
 * @create 2023-05-31 18:32
 * @Description: address mapper 测试
 */
@SpringBootTest
public class AddressMapperTests {
    @Resource
    private  AddressMapper addressMapper;
    @Test
    void insert() {
        Address address = new Address();
        address.setUid(6);
        address.setName("管理员");
        addressMapper.insert(address);
    }
    @Test
    void findCountByUid() {

        addressMapper.findCountByUid(6);
    }
}
