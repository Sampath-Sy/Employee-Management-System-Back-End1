package sampath.dev.emsbackend.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sampath.dev.emsbackend.dto.EmployeeDto;
import sampath.dev.emsbackend.entity.Employee;
import sampath.dev.emsbackend.exception.ResourceNotFoundException;
import sampath.dev.emsbackend.mapper.EmployeeMapper;
import sampath.dev.emsbackend.repository.EmployeeRepository;
import sampath.dev.emsbackend.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee is not found with given id"+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);

    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee>empList=employeeRepository.findAll();
        return empList.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDto) {
        Employee employee=employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee is not found with given id"+employeeId));
        employee.setFirstName(updatedEmployeeDto.getFirstName());
        employee.setLastName(updatedEmployeeDto.getLastName());
        employee.setEmail(updatedEmployeeDto.getEmail());
        Employee updatedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee is not found with given id"+employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
