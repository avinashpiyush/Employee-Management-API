package com.apiexample_sep.service;

import com.apiexample_sep.dto.EmployeeDto;
import com.apiexample_sep.entity.Employee;
import com.apiexample_sep.exception.ResourceNotFoundException;
import com.apiexample_sep.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String createEmployee(Employee emp) {
            employeeRepository.save(emp);
            return "done";


    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateRegistration(long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Record not found")
        );
        employee.setName(employeeDto.getName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setMobile(employeeDto.getMobile());

        Employee saveEmp = employeeRepository.save(employee);
        BeanUtils.copyProperties(saveEmp,employeeDto);
        return employeeDto;
    }

    public List<Employee> getALlEmployees(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<Employee> content = employees.getContent();

        return content;
    }

    public Employee getEmployeeById(long eid) {
        Employee employee = employeeRepository.findById(eid).orElseThrow(
                ()-> new ResourceNotFoundException("Record not found")
        );
        return employee;


    }
}
