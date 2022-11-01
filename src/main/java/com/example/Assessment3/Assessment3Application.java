package com.example.Assessment3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class Assessment3Application {
	Logger logger= LoggerFactory.getLogger(Assessment3Application.class);

	@GetMapping("/getEmp/{id}")
	public Employee getEmpById(@PathVariable int id) {
		List<Employee> emps=getEmps();
		Employee emp=emps.stream().
				filter(u->u.getId()==id).findAny().orElse(null);
		if(emp!=null){
			logger.info("employee found : {}",emp);
			return emp;
		}else{
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Employee Not Found with ID : {}",id);
			}
			return new Employee();
		}
	}


	private List<Employee> getEmps() {
		return Stream.of(new Employee(1, "John","23","IT"),
						new Employee(2, "Shyam","25","Management"),
						new Employee(3, "Rony","30","Sales"),
						new Employee(4, "Moni","22","IT"))
				.collect(Collectors.toList());
	}
	public static void main(String[] args) {
		SpringApplication.run(Assessment3Application.class, args);
	}

}
