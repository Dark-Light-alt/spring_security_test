package com.service;

import com.dao.RoleModuleBridgeDao;
import com.entity.Module;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class RoleModuleBridgeService {

    @Resource
    private RoleModuleBridgeDao roleModuleBridgeDao;

    public boolean assignModule(String roleId, Set<String> moduleIds) {

        roleModuleBridgeDao.removeAll(roleId);

        if (moduleIds.size() != 0) {
            return roleModuleBridgeDao.assignModule(roleId, moduleIds) != 0;
        }

        return true;
    }

    public List<Module> findAllModule(String roleId) {

        List<Module> parentModules = roleModuleBridgeDao.findAllModule(roleId, "0");

        recursionFindChildren(roleId, parentModules);

        return parentModules;
    }

    public List<String> findCheckedModuleId(String roleId) {
        return roleModuleBridgeDao.findCheckedModuleId(roleId);
    }

    private void recursionFindChildren(String roleId, List<Module> parentModules) {

        for (Module parentModule : parentModules) {
            if (parentModule.getChildrenCount() == 0) {
                continue;
            }

            List<Module> childrenModules = roleModuleBridgeDao.findAllModule(roleId, parentModule.getModuleId());
            parentModule.setChildren(childrenModules);
            recursionFindChildren(roleId, childrenModules);
        }
    }
}
