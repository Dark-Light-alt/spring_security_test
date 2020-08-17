package com.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "role_module_bridge")
public class RoleModuleBridge {

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "module_id")
    private String moduleId;
}
