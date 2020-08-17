package com.dao;

import com.entity.Module;
import com.entity.RoleModuleBridge;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

@org.apache.ibatis.annotations.Mapper
public interface RoleModuleBridgeDao extends Mapper<RoleModuleBridge> {

    /**
     * 查询角色所拥有的模块信息
     *
     * @param roleId   角色 id
     * @param moduleId 模块 id
     * @return
     */
    List<Module> findAllModule(String roleId, String moduleId);

    /**
     * 批量分配模块
     *
     * @param roleId
     * @param moduleIds
     * @return
     */
    int assignModule(String roleId, @Param("moduleIds") Set<String> moduleIds);

    /**
     * 根据角色 id 删除其模块
     *
     * @param roleId
     * @return
     */
    int removeAll(String roleId);

    /**
     * 根据角色 id 查询出选中的 moduleId
     * @param roleId
     * @return
     */
    List<String> findCheckedModuleId(String roleId);
}
