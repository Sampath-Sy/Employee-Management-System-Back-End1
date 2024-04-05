package sampath.dev.emsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sampath.dev.emsbackend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
