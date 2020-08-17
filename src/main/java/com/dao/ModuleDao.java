package com.dao;

import com.entity.Module;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ModuleDao extends Mapper<Module> {

    int remove(String moduleId);

    int changeEnable(String moduleId, Integer enable);

    List<Module> findAll(String moduleId);
}
