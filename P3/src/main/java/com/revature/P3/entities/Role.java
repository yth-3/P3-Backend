package com.revature.P3.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class Role {
    @Id
    private String roleId;

    @Column(name = "role", nullable = false)
    private String role;

    public Role() {
    }

    public Role(String roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
