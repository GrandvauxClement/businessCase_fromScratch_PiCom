package com.picom.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenricDAO<ENTITY> {

    List<ENTITY> findAll() throws SQLException;

    ENTITY findById(Long id) throws SQLException;

    boolean deleteById(Long id) throws SQLException;
}
