package com.controller;

import com.service.ConsumerRoleBridgeService;
import com.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("ConsumerRoleBridgeController")
public class ConsumerRoleBridgeController {

    @Resource
    private ConsumerRoleBridgeService consumerRoleBridgeService;

    @RequestMapping(value = "findRolesByConsumerId/{consumerId}", method = RequestMethod.GET)
    public Result findRolesByConsumerId(@PathVariable String consumerId) {

        Result result = new Result();

        result.putData("roles", consumerRoleBridgeService.findRolesByConsumerId(consumerId));

        result.success(200, "角色查询成功");

        return result;
    }

    @RequestMapping(value = "findRoles", method = RequestMethod.GET)
    public Result findRoles() {

        Result result = new Result();

        result.putData("roles", consumerRoleBridgeService.findRoles());

        result.success(200, "角色信息查询成功");

        return result;
    }

    @RequestMapping(value = "assignRole", method = RequestMethod.PUT)
    public Result assignRole(@RequestBody Map<String, ?> params) {

        Result result = new Result();

        String consumerId = String.valueOf(params.get("consumerId"));

        List<String> roles = (List<String>) params.get("roles");

        if (consumerRoleBridgeService.assignRole(consumerId,roles)) {
            result.success(200, "角色分配成功");
        } else {
            result.error(500, "角色分配失败");
        }

        return result;
    }
}
