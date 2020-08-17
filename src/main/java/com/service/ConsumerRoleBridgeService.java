package com.service;

import com.dao.ConsumerRoleBridgeDao;
import com.entity.ConsumerRoleBridge;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ConsumerRoleBridgeService {

    @Resource
    private ConsumerRoleBridgeDao consumerRoleBridgeDao;

    public List<Map<String, String>> findRolesByConsumerId(String consumerId) {
        return consumerRoleBridgeDao.findRolesByConsumerId(consumerId);
    }

    public List<Map<String, String>> findRoles() {
        return consumerRoleBridgeDao.findRoles();
    }

    public boolean assignRole(String consumerId, List<String> roles) {

        removeRoleByConsumerId(consumerId);

        ConsumerRoleBridge consumerRoleBridge = new ConsumerRoleBridge();

        consumerRoleBridge.setConsumerId(consumerId);

        for (int i = 0;i < roles.size(); i++) {
            consumerRoleBridge.setRoleId(roles.get(i));
            consumerRoleBridgeDao.insert(consumerRoleBridge);
        }

        return true;
    }

    private int removeRoleByConsumerId(String consumerId) {

        Example example = new Example(ConsumerRoleBridge.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("consumerId", consumerId);

        return consumerRoleBridgeDao.deleteByExample(example);
    }
}
