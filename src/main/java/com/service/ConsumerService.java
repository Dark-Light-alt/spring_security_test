package com.service;

import com.dao.ConsumerDao;
import com.entity.Consumer;
import com.github.pagehelper.PageHelper;
import com.util.MD5;
import com.util.Page;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ConsumerService {

    @Resource
    private ConsumerDao consumerDao;

    public boolean append(Consumer consumer) {

        if (findByUsernameCount(consumer.getUsername()) == 0) {

            consumer.setPassword(MD5.encode(consumer.getPassword()));
            consumer.setCreateTime(new Date());

            return consumerDao.insertSelective(consumer) != 0;
        }

        return false;
    }

    public boolean remove(String id) {
        return consumerDao.deleteByPrimaryKey(id) != 0;
    }

    public boolean update(Consumer consumer) {

        if (findByUsernameCount(consumer.getUsername()) == 0) {
            return consumerDao.updateByPrimaryKeySelective(consumer) != 0;
        }

        return false;
    }

    public boolean changeEndLoginTime(String username, Date endLoginTime) {
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setEndLoginTime(endLoginTime);

        Example example = new Example(Consumer.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("username", username);

        return consumerDao.updateByExampleSelective(consumer, example) != 0;
    }

    public Consumer findById(String id) {
        return consumerDao.selectByPrimaryKey(id);
    }

    public List<Consumer> findAll(Page page) {

        if (page.isFlag()) {
            PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        }

        Example example = new Example(Consumer.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andLike("name", "%" + page.getSearchs().get("name") == null ? "" : page.getSearchs().get("name") + "%");
        criteria.andLike("username", "%" + page.getSearchs().get("username") == null ? "" : page.getSearchs().get("username") + "%");

        return consumerDao.selectByExample(example);
    }

    public Consumer findByUsername(String username) {

        Example example = new Example(Consumer.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("username", username);

        return consumerDao.selectOneByExample(example);
    }

    public boolean changeEnable(String id, Integer isenable) {

        Example example = new Example(Consumer.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("id", id);

        Consumer consumer = new Consumer();
        consumer.setId(id);
        consumer.setIsenable(isenable);

        return consumerDao.updateByPrimaryKeySelective(consumer) != 0;
    }

    private int findByUsernameCount(String username) {

        Example example = new Example(Consumer.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("username", username);

        return consumerDao.selectCountByExample(example);
    }
}
