package com.controller;

import com.entity.Consumer;
import com.github.pagehelper.PageInfo;
import com.service.ConsumerService;
import com.util.Page;
import com.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("ConsumerController")
public class ConsumerController {

    @Resource
    private ConsumerService consumerService;

    @RolesAllowed("ADMIN")
    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public Result findAll(@RequestBody Page page) {

        Result result = new Result();

        List<Consumer> consumerList = consumerService.findAll(page);

        result.putData("consumerList", consumerList);

        PageInfo<Consumer> pageInfo = new PageInfo<Consumer>(consumerList);

        page.setLastPage(pageInfo.getPages());
        page.setTotal(pageInfo.getTotal());

        result.setPage(page);

        result.success(200, "用户信息查询成功");

        return result;
    }

    @RolesAllowed("ADMIN")
    @RequestMapping(value = "append", method = RequestMethod.PUT)
    public Result append(@RequestBody Consumer consumer) {

        Result result = new Result();

        if (consumerService.append(consumer)) {
            result.success(200, "用户信息添加成功");
        } else {
            result.error(500, "用户信息添加失败，用户名已存在");
        }

        return result;
    }

    @RolesAllowed("ADMIN")
    @RequestMapping(value = "changeEnable/{id}/{enable}", method = RequestMethod.GET)
    public Result changeEnable(@PathVariable String id, @PathVariable Integer enable) {

        Result result = new Result();

        if (consumerService.changeEnable(id, enable)) {
            result.success(200, enable == 0 ? "用户已启用" : "用户已禁用");
        } else {
            result.error(500, "用户状态修改失败");
        }

        return result;
    }

    @RolesAllowed("ADMIN")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id) {

        Result result = new Result();

        if (consumerService.remove(id)) {
            result.success(200, "用户信息删除成功");
        } else {
            result.error(500, "用户信息删除失败");
        }

        return result;
    }

    @RolesAllowed("ADMIN")
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {

        Result result = new Result();

        result.putData("consumer", consumerService.findById(id));

        result.success(200, "查询用户信息成功");

        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody Consumer consumer) {

        Result result = new Result();

        if (consumerService.update(consumer)) {
            result.success(200, "用户信息修改成功");
        } else {
            result.error(500, "用户信息修改失败，用户名已存在");
        }

        return result;
    }
}
