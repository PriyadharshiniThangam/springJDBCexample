package com.spring.dao;

import java.util.List;

import com.spring.model.Employee;

public interface EmployeeDAO {
public void insert(Employee employee);
public Employee getByid(int id);
public void update(Employee employee);

public void deleteByid(int id);

public List<Employee> getAll();

}
