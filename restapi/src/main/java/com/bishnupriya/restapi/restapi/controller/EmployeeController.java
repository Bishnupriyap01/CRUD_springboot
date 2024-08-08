package com.bishnupriya.restapi.restapi.controller;
import com.bishnupriya.restapi.restapi.dto.EmployeeDTO;
import com.bishnupriya.restapi.restapi.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

//operations
//GET/employees
//POST/employees
//DELETE/employees/{id}

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployess(){
        return employeeService.getAllEmployees();
    }
    //path variable
    @GetMapping(path="/{id}")
    public EmployeeDTO getEmployees(@PathVariable("id") Long employeeId){
        return employeeService.getEmployeeById(employeeId);

    }
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createNewEmployee(employeeDTO);
    }
    @DeleteMapping(path="/{id}")
    public boolean deleteEmployeeById(@PathVariable("id") Long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDTO updatedEmployeeDTO){
         EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, updatedEmployeeDTO);
         if (updatedEmployee == null) {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(updatedEmployee);
     }




//      path param
//    @GetMapping(path="/employees")
//    public String get(@PathParam("sortby")  String sortby,
//                      @PathParam("limit") Long limit){
//        return "Hello" + sortby + limit;
//
//    }


}
