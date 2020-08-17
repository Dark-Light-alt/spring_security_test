package com.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "consumer_role_bridge")
public class ConsumerRoleBridge {

    @Column(name = "consumer_id")
    private String consumerId;

    @Column(name = "role_id")
    private String roleId;
}
