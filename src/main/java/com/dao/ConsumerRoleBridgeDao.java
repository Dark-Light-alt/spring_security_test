package com.dao;

import com.entity.ConsumerRoleBridge;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface ConsumerRoleBridgeDao extends Mapper<ConsumerRoleBridge> {

    List<Map<String, String>> findRolesByConsumerId(String consumerId);

    List<Map<String, String>> findRoles();
}
