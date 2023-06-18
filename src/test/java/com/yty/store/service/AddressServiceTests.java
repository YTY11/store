package com.yty.store.service;

import com.yty.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Yang
 * @create 2023-05-31 19:30
 * @Description: 业务层测试
 */
@SpringBootTest
public class AddressServiceTests {
    @Resource
    private AddressService addressService;
    @Test
    void addNewAddress() {
        Address address = new Address();
        address.setAddress("河南省");
        address.setName("demo6");
        addressService.addNewAddress(11,"demo6",address);
    }
}
