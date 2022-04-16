package com.picom.models;

import com.picom.models.db.TableName;

public class Role extends AbstractEntity{
    private Long id;

    private String name;

    public Role(Long id, String name) {
        super(TableName.ROLE);
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }
}
