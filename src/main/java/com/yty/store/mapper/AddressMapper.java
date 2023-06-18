package com.yty.store.mapper;

import com.yty.store.entity.Address;

public interface AddressMapper {

    /**
     * 插入语句
     * @param record
     * @return
     */
    Integer insert(Address record);

    /**
     * 查询用户收货地址数量
     * @param uid
     * @return
     */
    Integer findCountByUid(Integer uid);

    int insertSelective(Address record);
}