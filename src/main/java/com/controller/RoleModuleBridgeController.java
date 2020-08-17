package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.service.RoleModuleBridgeService;
import com.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("RoleModuleBridgeController")
public class RoleModuleBridgeController {

    @Resource
    private RoleModuleBridgeService roleModuleBridgeService;

    @RequestMapping(value = "findAllModule/{roleId}", method = RequestMethod.GET)
    public Result findAllModule(@PathVariable String roleId) {

        Result result = new Result();

        result.putData("moduleList", roleModuleBridgeService.findAllModule(roleId));

        result.success(200, "模块查询成功");

        return result;
    }

    @RequestMapping(value = "findCheckedModuleId/{roleId}", method = RequestMethod.GET)
    public Result findCheckedModuleId(@PathVariable String roleId) {

        Result result = new Result();

        result.putData("moduleIds", roleModuleBridgeService.findCheckedModuleId(roleId));

        result.success(200, "拥有模块查询成功");

        return result;
    }

    @RequestMapping(value = "assignModule", method = RequestMethod.PUT)
    public Result assignModule(@RequestBody Map<String, String> params) {

        Result result = new Result();

        String roleId = params.get("roleId");

        Set<String> moduleIds = JSONArray.parseObject(params.get("moduleIds"), Set.class);

        if (roleModuleBridgeService.assignModule(roleId, moduleIds)) {
            result.success(200, "角色模块分配成功");
        } else {
            result.error(500, "角色模块分配失败");
        }

        return result;
    }
}
