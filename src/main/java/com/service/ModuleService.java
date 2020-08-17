package com.service;

import com.dao.ModuleDao;
import com.entity.Module;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService {

    @Resource
    private ModuleDao moduleDao;

    /**
     * 添加模块信息
     *
     * @param module
     * @return
     */
    public boolean append(Module module) {

        if (null == module.getParentId() || module.getParentId().equals("0")) {
            module.setModuleLevel(1);
            module.setParentId("0");
        } else {
            module.setModuleLevel((findById(module.getParentId()).getModuleLevel() + 1));
        }

        return moduleDao.insertSelective(module) != 0;
    }

    /**
     * 删除模块
     *
     * @param modeulId
     * @return
     */
    public boolean remove(String modeulId) {
        return moduleDao.remove(modeulId) != 0;
    }

    /**
     * 根据 id 更改模块的状态信息
     *
     * @param moduleId 模块 id
     * @param enable   状态值
     * @return
     */
    public boolean changeEnable(String moduleId, Integer enable) {
        return moduleDao.changeEnable(moduleId, enable) != 0;
    }

    /**
     * 修改模块信息
     *
     * @param module
     * @return
     */
    public boolean update(Module module) {

        if (null == module.getParentId() || module.getParentId().equals("0")) {
            module.setModuleLevel(1);
            module.setParentId("0");
        } else {
            module.setModuleLevel((findById(module.getParentId()).getModuleLevel() + 1));
        }

        return moduleDao.updateByPrimaryKeySelective(module) != 0;
    }

    /**
     * 根据 id 查询模块信息
     *
     * @param moduleId 模块 id
     * @return
     */
    public Module findById(String moduleId) {
        return moduleDao.selectByPrimaryKey(moduleId);
    }

    /**
     * 查询到所有功能性的模块
     * @return
     */
    public List<Module> findAllFunctionModule() {

        Example example = new Example(Module.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andIsNotNull("component");

        return moduleDao.selectByExample(example);
    }

    /**
     * 查询模块所有模块信息
     *
     * @return
     */
    public List<Module> findAll() {

        List<Module> parentModules = moduleDao.findAll("0");

        //递归查询子级
        recursionFindChildren(parentModules);

        return parentModules;
    }

    /**
     * 查询所有的父级模块，以供添加使用
     * 限制查询 1 ~ 2 的模块等级，是因为不能有 4 级模块
     *
     * @return
     */
    public List<Module> findAllParentModule() {

        Example example = new Example(Module.class);

        example.setOrderByClause("SORT ASC");

        Example.Criteria criteria = example.createCriteria();

        criteria.andBetween("moduleLevel", 1, 2);

        return moduleDao.selectByExample(example);
    }

    /**
     * 根据递归查询出父级模块的子级模块
     *
     * @param parentModules 父级模块集
     */
    private void recursionFindChildren(List<Module> parentModules) {

        for (Module parentModule : parentModules) {
            if (parentModule.getChildrenCount() == 0) {
                continue;
            }

            List<Module> childrenModules = moduleDao.findAll(parentModule.getModuleId());
            parentModule.setChildren(childrenModules);
            recursionFindChildren(childrenModules);
        }
    }
}
