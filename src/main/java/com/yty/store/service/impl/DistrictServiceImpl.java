package com.yty.store.service.impl;

import com.yty.store.entity.District;
import com.yty.store.mapper.DistrictMapper;
import com.yty.store.service.DistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yang
 * @create 2023-06-01 18:22
 * @Description: 区域功能业务实现类
 */
@Service
public class DistrictServiceImpl implements DistrictService {
    @Resource
    private DistrictMapper districtMapper;

    /**
     * 根据parent 获取相关区域
     * @param perent
     * @return 相关区域
     */
    @Override
    public List<District> getByParent(String perent) {
        List<District> data = districtMapper.findByParent(perent);
        return data;
    }
}
