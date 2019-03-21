package com.spring.main;

import java.util.List;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dao.EmployeeDAO;
import com.spring.model.Employee;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		 
		EmployeeDAO employeeDAO = ctx.getBean("employeeDAO", EmployeeDAO.class);
		
		//Run some tests for JDBC CRUD operations
		Employee emp = new Employee();
		int rand = new Random().nextInt(1000);
		emp.setid(rand);
		emp.setname("priya");
		emp.setrole("Developer");
		
		
		employeeDAO.insert(emp);
		
				Employee emp1 = employeeDAO.getByid(rand);
		System.out.println("Employee Retrieved::"+emp1);
		
		
		emp.setrole("CEO");
		employeeDAO.update(emp);
		
		
		List<Employee> empList = employeeDAO.getAll();
		System.out.println(empList);
		
		
		employeeDAO.deleteById(rand);
		
		
		ctx.close();
		
		System.out.println("DONE");
	}

}
