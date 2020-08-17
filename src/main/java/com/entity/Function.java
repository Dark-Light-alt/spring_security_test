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
public class Function {

    @Id
    @KeySql(sql = "select sys_guid() from dual", order = ORDER.BEFORE)
    private String functionId;

    @Column(name = "function_name")
    private String functionName;

    @Column(name = "function_path")
    private String functionPath;

    @Column(name = "create_time")
    private Date createTime;

    @Column
    private String describe;
}
