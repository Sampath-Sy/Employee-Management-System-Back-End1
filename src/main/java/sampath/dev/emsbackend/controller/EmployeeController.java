package sampath.dev.emsbackend.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sampath.dev.emsbackend.dto.EmployeeDto;
import sampath.dev.emsbackend.service.EmployeeService;

import java.util.List;
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee =employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long Id){
        return  ResponseEntity.ok(employeeService.getEmployeeById(Id));
    }

    @GetMapping("/all/employees")
    public ResponseEntity<List<EmployeeDto>> findAllEmployees(){
        List<EmployeeDto> empList=employeeService.getAllEmployees();
        return ResponseEntity.ok(empList);
    }
    @PutMapping("/{Id}")
    public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable Long Id,@RequestBody EmployeeDto updatedEmployeeDto){
       EmployeeDto employeeDto= employeeService.updateEmployee(Id,updatedEmployeeDto);
       return ResponseEntity.ok(employeeDto);

    }
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long Id){
        employeeService.deleteEmployee(Id);
       return  ResponseEntity.ok("Employee deleted successfully");

    }

}
