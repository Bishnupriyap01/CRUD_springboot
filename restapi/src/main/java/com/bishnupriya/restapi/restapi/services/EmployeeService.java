package com.bishnupriya.restapi.restapi.services;

import com.bishnupriya.restapi.restapi.dto.EmployeeDTO;
import com.bishnupriya.restapi.restapi.entities.EmployeeEntity;
import com.bishnupriya.restapi.restapi.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    //constructor injection
    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity = employeeRepository.getById(id);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity= modelMapper.map(employeeDTO, EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class );
    }
    public List<EmployeeDTO> getAllEmployees(){
       return employeeRepository
               .findAll()
               .stream()
               .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
               .collect(Collectors.toList());

    }
    public boolean deleteEmployeeById(Long id){
        boolean isPresent = employeeRepository.existsById(id);
        if(!isPresent) return false;
        employeeRepository.deleteById(id);
        return true;
    }
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        boolean isPresent = employeeRepository.existsById(id);
        if (!isPresent) return null;

        EmployeeEntity existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setName(updatedEmployeeDTO.getName());
            existingEmployee.setDoj(updatedEmployeeDTO.getDoj());
            EmployeeEntity savedEmployee = employeeRepository.save(existingEmployee);
            return modelMapper.map(savedEmployee, EmployeeDTO.class);
        }
        return null;
    }
    public List<EmployeeDTO> findByName(String name) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findByName_(name);
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }


}
