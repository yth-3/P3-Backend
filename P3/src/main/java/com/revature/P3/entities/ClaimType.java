package com.revature.P3.entities;

import javax.persistence.*;

@Entity
@Table(name = "claim_types")
public class ClaimType {
    @Id
    private String typeId;

    @Column(name = "type", nullable = false)
    private String type;

    public ClaimType() {
    }

    public ClaimType(String typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClaimType{" +
                "typeId='" + typeId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
