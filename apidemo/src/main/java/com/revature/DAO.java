package com.revature;
import java.util.List;
import java.sql.SQLException;

public interface DAO<T>{
    T get(String id ) throws SQLException;
    List<T> getAll() throws SQLException;
    int save(T t) throws SQLException;
    int update(T t) throws SQLException;
    int delete(String id) throws SQLException;
}
