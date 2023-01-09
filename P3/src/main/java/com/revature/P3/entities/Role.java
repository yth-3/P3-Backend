package com.revature.P3.entities;

import com.revature.P3.enums.Roles;
import com.revature.P3.utils.custom_exceptions.InvalidUserException;

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

    public Role(Roles role) {
        if (role.equals(Roles.Patient)) {
            this.roleId = "0";
        }
        else if (role.equals(Roles.Nurse)) {
            this.roleId = "1";
        }
        else if (role.equals(Roles.Doctor)) {
            this.roleId = "2";
        }
        else if (role.equals(Roles.Insurer)) {
            this.roleId = "3";
        }
        else if (role.equals(Roles.Admin)) {
            this.roleId = "4";
        }
        else {
            throw new InvalidUserException();
        }

        this.role = role.toString();
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
