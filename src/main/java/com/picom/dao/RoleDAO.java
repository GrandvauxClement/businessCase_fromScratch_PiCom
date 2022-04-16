package com.picom.dao;

import com.picom.models.Role;
import com.picom.models.db.TableName;

public class RoleDAO extends AbstractGenericDAO<Role>{

    public RoleDAO() {
        super(TableName.ROLE);
    }
}
