package com.spring.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.model.Employee;

public class EmployeeJDBCTemplateImpl implements EmployeeDAO {

		private DataSource dataSource;

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
		@Override
		public void insert(Employee employee) {
			String query = "insert into Employee (id, name, role) values (?,?,?)";
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			Object[] args = new Object[] {employee.getid(), employee.getname(), employee.getrole()};
			
			int out = jdbcTemplate.update(query, args);
			
			if(out !=0){
				System.out.println("Employee id saved="+employee.getid());
			}else System.out.println("failed to save employee="+employee.getid());
		}

		@Override
		public Employee getByid(int id) {
			String query = "select id, name, role from Employee where id = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			//using RowMapper anonymous class, we can create a separate RowMapper for reuse
			Employee emp = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Employee>(){

				@Override
				public Employee mapRow(ResultSet rs, int rowNum)throws SQLException {
					Employee emp = new Employee();
					emp.setid(rs.getInt("id"));
					emp.setname(rs.getString("name"));
					emp.setrole(rs.getString("role"));
					return emp;
				}});
			
			return emp;
		}

		@Override
		public void update(Employee employee) {
			String query = "update Employee set name=?, role=? where id=?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			Object[] args = new Object[] {employee.getname(), employee.getrole(), employee.getid()};
			
			int out = jdbcTemplate.update(query, args);
			if(out !=0){
				System.out.println("Employee updated with id="+employee.getid());
			}else System.out.println("No Employee found with id="+employee.getid());
		}

		@Override
		public void deleteByid(int id) {

			String query = "delete from Employee where id=?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			int out = jdbcTemplate.update(query, id);
			if(out !=0){
				System.out.println("Employee deleted with id="+id);
			}else System.out.println("No Employee found with id="+id);
		}

		@Override
		public List<Employee> getAll() {
			String query = "select id, name, role from Employee";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Employee> empList = new ArrayList<Employee>();

			List<Map<String,Object>> empRows = jdbcTemplate.queryForList(query);
			
			for(Map<String,Object> empRow : empRows){
				Employee emp = new Employee();
				emp.setid(Integer.parseInt(String.valueOf(empRow.get("id"))));
				emp.setname(String.valueOf(empRow.get("name")));
				emp.setrole(String.valueOf(empRow.get("role")));
				empList.add(emp);
			}
			return empList;
		}
}
