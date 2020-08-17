package com.controller;

import com.entity.Role;
import com.github.pagehelper.PageInfo;
import com.service.RoleService;
import com.util.Page;
import com.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("RoleController")
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public Result findAll(@RequestBody Page page) {

        Result result = new Result();

        List<Role> roleList = roleService.findAll(page);

        result.putData("roleList", roleList);

        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);

        page.setLastPage(pageInfo.getPages());
        page.setTotal(pageInfo.getTotal());

        result.setPage(page);

        result.success(200, "用户信息查询成功");

        return result;
    }

    @RequestMapping(value = "append", method = RequestMethod.PUT)
    public Result append(@RequestBody Role role) {

        Result result = new Result();

        if (roleService.append(role)) {
            result.success(200, "角色信息添加成功");
        } else {
            result.error(500, "角色信息添加失败，角色名已存在");
        }

        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody Role role) {

        Result result = new Result();

        if (roleService.update(role)) {
            result.success(200, "角色信息修改成功");
        } else {
            result.error(500, "角色信息修改失败，角色名已存在");
        }

        return result;
    }

    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {

        Result result = new Result();

        result.putData("role", roleService.findById(id));

        result.success(200, "查询角色信息成功");

        return result;
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id) {

        Result result = new Result();

        if (roleService.remove(id)) {
            result.success(200, "角色信息删除成功");
        } else {
            result.error(500, "角色信息删除失败");
        }

        return result;
    }

    @RequestMapping(value = "changeEnable/{id}/{enable}", method = RequestMethod.GET)
    public Result changeEnable(@PathVariable String id, @PathVariable Integer enable) {

        Result result = new Result();

        if (roleService.changeEnable(id, enable)) {
            result.success(200, enable == 0 ? "角色已启用" : "角色已禁用");
        } else {
            result.error(500, "角色状态修改失败");
        }

        return result;
    }
}
