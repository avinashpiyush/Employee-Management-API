package com.apiexample_sep.controller;

import com.apiexample_sep.dto.APIResponce;
import com.apiexample_sep.dto.EmployeeDto;
import com.apiexample_sep.entity.Employee;
import com.apiexample_sep.repository.EmployeeRepository;
import com.apiexample_sep.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    // http://localhost:8080/api/v1/employee/save
    @PostMapping("/save")
    public ResponseEntity<APIResponce> createEmployee(
            @Valid @RequestBody Employee emp,
            BindingResult result)
    {    APIResponce<String> responce=new APIResponce<>();
        if (result.hasErrors()){
            responce.setStatus(500);
            responce.setMessage("invalid input");
            responce.setData(result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(responce,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String status = employeeService.createEmployee(emp);

        if (status.equals("done")) {

            responce.setMessage("transcation completed");
            responce.setData("done");
            responce.setStatus(201);
            return new ResponseEntity<>(responce, HttpStatus.CREATED);
        } else {
            responce.setMessage("transcation failed");
            responce.setData("duplicate data");
            responce.setStatus(500);
            return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http:localhost//8080/api/v1/employee/delete?id=7
    @DeleteMapping("/delete")
    public ResponseEntity<APIResponce> deleteEmployee(

            @RequestParam long id
    ) {
        employeeService.deleteEmployee(id);
        APIResponce<String> responce = new APIResponce<>();
        responce.setMessage("transaction completed");
        responce.setData("deleted");
        responce.setStatus(201);

        return new ResponseEntity<>(responce, HttpStatus.CREATED);

    }

    //http://localhost:8080/api/v1/employee/update/32
    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponce<EmployeeDto>> updateEmployeeRecord(
            @RequestBody EmployeeDto employeeDto,
            @PathVariable long id
    ) {

        EmployeeDto dto = employeeService.updateRegistration(id, employeeDto);
        APIResponce<EmployeeDto> responce = new APIResponce<>();
        responce.setMessage("updated");
        responce.setData(employeeDto);
        responce.setStatus(200);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/all?pageNo=0&pageSize=5
    @GetMapping("/all")
    public ResponseEntity<APIResponce<List<Employee>>> getEmployees(
           @RequestParam(name="pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "5",required = false) int pageSize
    ) {

        List<Employee> employees = employeeService.getALlEmployees(pageNo,pageSize);
        APIResponce<List<Employee>> responce = new APIResponce<>();
        responce.setMessage("Done");
        responce.setData(employees);
        responce.setStatus(200);

        return new ResponseEntity<>(responce, HttpStatus.OK);
    }
         //http://localhost:8080/api/v1/employee/id/3
        @GetMapping("/id/{eid}")
        public ResponseEntity<APIResponce<Employee>> getEmployeeById(

                @PathVariable long eid
        ){
        Employee employee=employeeService.getEmployeeById(eid);
            APIResponce<Employee> response=new APIResponce<>();
            response.setMessage("review");
            response.setData(employee);
            response.setStatus(200);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }



    }

