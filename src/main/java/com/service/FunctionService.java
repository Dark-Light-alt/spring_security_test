package com.service;

import com.dao.FunctionDao;
import com.entity.Function;
import com.github.pagehelper.PageHelper;
import com.util.Page;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FunctionService {

    @Resource
    private FunctionDao functionDao;

    public boolean append(Function function) {
        function.setCreateTime(new Date());

        return functionDao.insertSelective(function) != 0;
    }

    public boolean remove(String id) {
        return functionDao.deleteByPrimaryKey(id) != 0;
    }

    public boolean update(Function function) {
        return functionDao.updateByPrimaryKeySelective(function) != 0;
    }

    public Function findById(String id) {
        return functionDao.selectByPrimaryKey(id);
    }

    public List<Function> findAll(Page page) {

        if (page.isFlag()) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }

        Example example = new Example(Function.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andLike("functionName","%" + page.getSearchs().get("functionName") == null ? "" : page.getSearchs().get("functionName") + "%");

        return functionDao.selectByExample(example);
    }
}
