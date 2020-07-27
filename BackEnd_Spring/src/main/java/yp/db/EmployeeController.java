package yp.db;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/")
    public String home(){
        return "Testing React-Spring-Firebase";
    }
	
	@GetMapping("/getEmployeeDetails/{name}")
	public Employee getEmployee(@PathVariable("name") String name) throws InterruptedException, ExecutionException {
		return employeeService.getEmployeeDetails(name);
	}
	
	@GetMapping("/getEmployeeDetails")
	public List<Employee> getAllDetails() throws InterruptedException, ExecutionException {
		return employeeService.getAllDetails();
	}

	@PostMapping("/createEmployee")
	public String createEmployee(@RequestBody Employee employee) throws InterruptedException, ExecutionException {
		return employeeService.saveEmployeeDetails(employee);
	}

	@PutMapping("/updateEmployee")
	public String updateEmployee(@RequestBody Employee employee) throws InterruptedException, ExecutionException {
		return employeeService.updateEmployeeDetails(employee);
	}

	@DeleteMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam String name) {
		return employeeService.deleteEmployee(name);
	}

}
