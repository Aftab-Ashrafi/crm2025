package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class EmployeeService {
    private  final EmployeeRepository employeeRepository;
    private  final      ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository) {
       this.employeeRepository = employeeRepository;
       this.modelMapper =new ModelMapper();
    }
    //create add employee method for employee entity
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee= mapToEntity(employeeDto);
        Employee emp= employeeRepository.save(employee);
        EmployeeDto employeeDto1= mapToDto(emp);
        return employeeDto1;
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee =mapToEntity(dto);
        employee.setId(id);
        Employee updateEmployee =employeeRepository.save(employee);
        EmployeeDto employeeDto=mapToDto(updateEmployee);
        return employeeDto;
    }

    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
      Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(page);
       List<Employee>employees= all.getContent();
        List<EmployeeDto> dto=employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return dto;

    }



    EmployeeDto mapToDto(Object employee){

    EmployeeDto dto =modelMapper.map(employee, EmployeeDto.class);

        return dto;
    }
    Employee mapToEntity(EmployeeDto dto){
        Employee employee=modelMapper.map(dto, Employee.class);
        return employee;

    }

    public EmployeeDto getEmployeeById(long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                //supplier concept of functional Interface.
                ()->new ResourceNotFound("Record not found by Id"+empId)
        );
        EmployeeDto dto=mapToDto(employee);
     return dto;
    }
}
