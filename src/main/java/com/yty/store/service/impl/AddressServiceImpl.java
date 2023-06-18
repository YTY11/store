package com.yty.store.service.impl;

import com.yty.store.entity.Address;
import com.yty.store.mapper.AddressMapper;
import com.yty.store.service.AddressService;
import com.yty.store.service.ex.AddressCountException;
import com.yty.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Yang
 * @create 2023-05-31 19:18
 * @Description: address 业务层实现
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Value("${user.address.max-count}")
    private Integer MAX_COUNT;
    @Override
    public Integer addNewAddress(Integer uid, String username, Address address) {

        Integer count = addressMapper.findCountByUid(uid);
        // 地址数超出上限
        if(count > MAX_COUNT){
            throw new AddressCountException("地址数已到上限");
        }
        address.setUid(uid);
        address.setIsDefault(count == 0 ? 1 : 0);


        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        Integer row = addressMapper.insert(address);
        if(row != 1){
            throw new InsertException("地址设置失败");
        }
        return row;
    }
}
