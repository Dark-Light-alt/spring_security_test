package com.controller;

import com.entity.Module;
import com.service.ModuleService;
import com.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("ModuleController")
public class ModuleController {

    @Resource
    private ModuleService moduleService;

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public Result findAll() {

        Result result = new Result();

        List<Module> moduleList = moduleService.findAll();

        result.putData("moduleList", moduleList);

        result.success(200, "模块信息查询成功");

        return result;
    }

    @RequestMapping(value = "append", method = RequestMethod.PUT)
    public Result append(@RequestBody Module module) {

        Result result = new Result();

        if (moduleService.append(module)) {
            result.success(200, "模块信息添加成功");
        } else {
            result.error(500, "模块信息添加失败");
        }

        return result;
    }

    @RequestMapping(value = "findAllParentModule", method = RequestMethod.GET)
    public Result findAllParentModule() {

        Result result = new Result();

        result.putData("parentModuleList", moduleService.findAllParentModule());

        result.success(200, "所有父级查询成功");

        return result;
    }

    @RequestMapping(value = "remove/{moduleId}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String moduleId) {

        Result result = new Result();

        if (moduleService.remove(moduleId)) {
            result.success(200, "模块信息删除成功");
        } else {
            result.error(500, "模块信息删除失败");
        }

        return result;
    }

    @RequestMapping(value = "changeEnable/{moduleId}/{enable}", method = RequestMethod.GET)
    public Result changeEnable(@PathVariable String moduleId, @PathVariable Integer enable) {

        Result result = new Result();

        if (moduleService.changeEnable(moduleId, enable)) {
            result.success(200, enable == 0 ? "模块状态已启用" : "模块状态已禁用");
        } else {
            result.error(500, "模块状态更改失败");
        }

        return result;
    }

    @RequestMapping(value = "findById/{moduleId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String moduleId) {

        Result result = new Result();

        result.putData("module", moduleService.findById(moduleId));

        result.success(200, "模块信息查询成功");

        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody Module module) {

        Result result = new Result();

        if (moduleService.update(module)) {
            result.success(200, "模块信息修改成功");
        } else {
            result.error(500, "模块信息修改失败");
        }

        return result;
    }

    @RequestMapping(value = "findAllFunctionModule",method = RequestMethod.GET)
    public Result findAllFunctionModule() {

        Result result = new Result();

        result.putData("moduleList",moduleService.findAllFunctionModule());

        result.success(200,"模块信息查询成功");

        return result;
    }
}
