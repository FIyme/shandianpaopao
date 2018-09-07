package com.ssh.controller;

import com.ssh.entity.Banner;
import com.ssh.entity.First;
import com.ssh.service.FirstService;
import com.ssh.utils.CreateOrderID;
import com.ssh.utils.enums.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "first")
@Controller
@RequestMapping(value = "/first")
public class FirstController {
    @Autowired
    private FirstService firstService;

    @ApiImplicitParams({})
    @RequestMapping(value = "createOrder", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map createOrder(@RequestBody First first) {
        Map map = new HashMap();
        first.setOrderId(CreateOrderID.getCurrentTimeWithoutSpace());
        first.setState("0");
        first.setCreateTime(CreateOrderID.getCurrentTime());
        firstService.save(first);
        map.put("msg", "执行成功！");
        return map;
    }

    @ApiImplicitParams({})
    @RequestMapping(value = "orderByState", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map orderByState(String openId, String state) {
        Map map = new HashMap();
        List<First> list = firstService.orderByState(openId, state);
        map.put("content", list);
        map.put("msg", ResultStatus.SUCCESS.getCode());
        map.put("msg", "执行成功！");
        return map;
    }

}
