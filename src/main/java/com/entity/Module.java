package com.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table
public class Module {

    @Id
    @KeySql(sql = "select sys_guid() from dual", order = ORDER.BEFORE)
    private String moduleId;

    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "module_level")
    private Integer moduleLevel;

    @Column(name = "router_path")
    private String routerPath;

    @Column(name = "router_name")
    private String routerName;

    @Column
    private String component;

    @Column(name = "parent_id")
    private String parentId;

    @Column
    private String icon;

    @Column
    private Integer sort;

    @Column
    private Integer isenable;

    @Transient
    private Integer childrenCount;

    @Transient
    private Integer isHave;

    private List<Module> children;
}
