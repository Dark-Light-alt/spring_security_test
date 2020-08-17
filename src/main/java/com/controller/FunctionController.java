package com.controller;

import com.entity.Function;
import com.entity.ModuleFunctionBridge;
import com.github.pagehelper.PageInfo;
import com.service.FunctionService;
import com.service.ModuleFunctionBridgeService;
import com.util.Page;
import com.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("FunctionController")
public class FunctionController {

    @Resource
    private FunctionService functionService;

    @Resource
    private ModuleFunctionBridgeService moduleFunctionBridgeService;

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public Result findAll(@RequestBody Page page) {

        Result result = new Result();

        List<Function> functionList = functionService.findAll(page);

        result.putData("functionList", functionList);

        PageInfo<Function> pageInfo = new PageInfo<Function>(functionList);

        page.setLastPage(pageInfo.getPages());
        page.setTotal(pageInfo.getTotal());

        result.setPage(page);

        result.success(200, "资源信息查询成功");

        return result;

    }

    @RequestMapping(value = "append", method = RequestMethod.PUT)
    public Result append(@RequestBody Map<String,String> params) {

        Result result = new Result();

        Function function = new Function();
        function.setFunctionName(params.get("functionName"));
        function.setFunctionPath(params.get("functionPath"));
        function.setDescribe(params.get("describe"));

        if (functionService.append(function)) {
            ModuleFunctionBridge moduleFunctionBridge = new ModuleFunctionBridge();
            moduleFunctionBridge.setModuleId(params.get("moduleId"));
            moduleFunctionBridge.setFunctionId(function.getFunctionId());
            moduleFunctionBridgeService.append(moduleFunctionBridge);
            result.success(200, "资源信息添加成功");
        } else {
            result.error(500, "资源信息添加失败");
        }

        return result;
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id) {

        Result result = new Result();

        if (functionService.remove(id)) {
            result.success(200, "资源信息删除成功");
        } else {
            result.error(500, "资源信息删除失败");
        }

        return result;
    }

    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {

        Result result = new Result();

        result.putData("fun", functionService.findById(id));
        result.success(200, "资源信息查询成功");
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody Map<String,String> params) {

        Result result = new Result();

        Function function = new Function();
        function.setFunctionId(params.get("functionId"));
        function.setFunctionName(params.get("functionName"));
        function.setFunctionPath(params.get("functionPath"));
        function.setDescribe(params.get("describe") == null ? "" : params.get("describe"));

        if (functionService.update(function)) {
            ModuleFunctionBridge moduleFunctionBridge = new ModuleFunctionBridge();
            moduleFunctionBridge.setModuleId(params.get("moduleId"));
            moduleFunctionBridge.setFunctionId(function.getFunctionId());
            moduleFunctionBridgeService.update(moduleFunctionBridge);
            result.success(200, "资源信息修改成功");
        } else {
            result.error(500, "资源信息修改失败");
        }

        return result;
    }
}
