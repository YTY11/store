package com.yty.store.service;

import com.yty.store.entity.Address;

/**
 * @author Yang
 * @create 2023-05-31 19:16
 * @Description: address 业务层接口
 */
public interface AddressService {
    Integer addNewAddress(Integer uid, String username, Address address);
}
