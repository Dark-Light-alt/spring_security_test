package com.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Table
public class Consumer implements UserDetails {

    @Id
    @KeySql(sql = "select sys_guid() from dual", order = ORDER.BEFORE)
    private String id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String password;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "END_LOGIN_TIME")
    private Date endLoginTime;

    @Column
    private Integer isenable;

    private List<GrantedAuthority> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getIsenable() == 0;
    }
}
