package com.yty.store.controller;

import com.yty.store.entity.Address;
import com.yty.store.service.AddressService;
import com.yty.store.utils.JSONResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Yang
 * @create 2023-05-31 21:04
 * @Description: 地址 控制层
 */
@RestController
@RequestMapping("/api")
public class AddressController extends BaseController{
    @Resource
    private AddressService addressService;

    /**
     * 添加地址
     * @param address
     * @param httpSession
     * @return
     */
    @PostMapping("/addNewAddress")
    public JSONResult<Void> addNewAddress(Address address, HttpSession httpSession){
        addressService.addNewAddress(getUidSession(httpSession), getUserNameSession(httpSession), address);

        return new JSONResult<>(OK, "地址添加成功");
    }
}
