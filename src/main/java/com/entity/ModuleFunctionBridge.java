package com.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "module_function_bridge")
public class ModuleFunctionBridge {

    @Column(name = "module_id")
    private String moduleId;

    @Column(name = "function_id")
    private String functionId;
}
