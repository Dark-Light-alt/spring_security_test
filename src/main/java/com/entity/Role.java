package com.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table
public class Role {

    @Id
    @KeySql(sql = "select sys_guid() from dual", order = ORDER.BEFORE)
    private String roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_label")
    private String roleLabel;

    @Column(name = "create_time")
    private Date createTime;

    @Column
    private Integer isenable;
}
