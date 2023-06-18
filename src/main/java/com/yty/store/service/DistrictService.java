package com.yty.store.service;

import com.yty.store.entity.District;

import java.util.List;

/**
 * @author Yang
 * @create 2023-06-01 18:20
 * @Description: 区域业务层接口
 */
public interface DistrictService {

    /**
     * 根据perent查询区域
     * @param perent
     * @return
     */
    List<District> getByParent(String perent);
}
