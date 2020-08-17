package com.service;

import com.dao.RoleDao;
import com.entity.Role;
import com.github.pagehelper.PageHelper;
import com.util.Page;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleDao roleDao;

    private static final String ROLE_NAME_PREFIX = "ROLE_";

    public boolean append(Role role) {

        String newRoleName = role.getRoleName().toUpperCase();

        if (newRoleName.indexOf(ROLE_NAME_PREFIX) == -1) {
            newRoleName = ROLE_NAME_PREFIX + newRoleName;
        }

        if (findByRoleNameCount(newRoleName) == 0) {
            role.setRoleName(newRoleName);
            role.setCreateTime(new Date());

            return roleDao.insert(role) != 0;
        }

        return false;
    }

    public boolean remove(String id) {
        return roleDao.deleteByPrimaryKey(id) != 0;
    }

    public boolean update(Role role) {

        String newRoleName = role.getRoleName().toUpperCase();

        if (newRoleName.indexOf(ROLE_NAME_PREFIX) == -1) {
            newRoleName = ROLE_NAME_PREFIX + newRoleName;
        }

        Role beforRole = findByRoleName(newRoleName);

        if (beforRole == null) {
            role.setRoleName(newRoleName);
            return roleDao.updateByPrimaryKeySelective(role) != 0;
        } else {
            if (beforRole.getRoleId().equals(role.getRoleId())) {
                role.setRoleName(null);
                return roleDao.updateByPrimaryKeySelective(role) != 0;
            }
            return false;
        }
    }

    public Role findById(String id) {
        return roleDao.selectByPrimaryKey(id);
    }

    public List<Role> findAll(Page page) {

        if (page.isFlag()) {
            PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        }

        Example example = new Example(Role.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andLike("roleLabel", "%" + page.getSearchs().get("roleLabel") == null ? "" : page.getSearchs().get("roleLabel") + "%");

        return roleDao.selectByExample(example);
    }

    public boolean changeEnable(String id, Integer enable) {

        Example example = new Example(Role.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("roleId", id);

        Role role = new Role();
        role.setRoleId(id);
        role.setIsenable(enable);

        return roleDao.updateByPrimaryKeySelective(role) != 0;
    }

    private int findByRoleNameCount(String roleName) {

        Example example = new Example(Role.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("roleName", roleName);

        return roleDao.selectCountByExample(example);
    }

    private Role findByRoleName(String roleName) {

        Example example = new Example(Role.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("roleName", roleName);

        return roleDao.selectOneByExample(example);
    }
}
