package com.yty.store.controller;

import com.yty.store.entity.District;
import com.yty.store.service.DistrictService;
import com.yty.store.utils.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yang
 * @create 2023-06-01 18:29
 * @Description: 区域控制层
 */
@RestController
@RequestMapping("/api")
public class DistrictController extends BaseController{
    @Resource
    private DistrictService districtService;

    /**
     * 根据parent 获取区域（省市县）数据
     * @param parent
     * @return
     */
    @GetMapping("/getByParent")
    public JSONResult<List<District>> getByParent(String parent){
        List<District> data = districtService.getByParent(parent);
        return new JSONResult<>(OK,"获取成功", data);
    }
}
