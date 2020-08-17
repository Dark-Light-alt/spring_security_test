package com.service;

import com.dao.ModuleFunctionBridgeDao;
import com.entity.ModuleFunctionBridge;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class ModuleFunctionBridgeService {

    @Resource
    private ModuleFunctionBridgeDao moduleFunctionBridgeDao;

    public boolean append(ModuleFunctionBridge moduleFunctionBridge) {
        return moduleFunctionBridgeDao.insert(moduleFunctionBridge) != 0;
    }

    public boolean update(ModuleFunctionBridge moduleFunctionBridge) {

        Example example = new Example(ModuleFunctionBridge.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("functionId",moduleFunctionBridge.getFunctionId());

        return moduleFunctionBridgeDao.updateByExample(moduleFunctionBridge,example) != 0;
    }

    public ModuleFunctionBridge findByFunctionId(String functionId) {

        Example example = new Example(ModuleFunctionBridge.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("functionId",functionId);

        return moduleFunctionBridgeDao.selectOneByExample(example);
    }
}
