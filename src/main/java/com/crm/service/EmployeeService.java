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


@SuppressWarnings("ALL")
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
        return mapToDto(emp);
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee =mapToEntity(dto);
        employee.setId(id);
        Employee updateEmployee =employeeRepository.save(employee);
        return mapToDto(updateEmployee);
    }

    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
      Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(page);
       List<Employee>employees= all.getContent();
        return employees.stream().map(this::mapToDto).collect(Collectors.toList());

    }



    EmployeeDto mapToDto(Object employee){

        return modelMapper.map(employee, EmployeeDto.class);
    }
    Employee mapToEntity(EmployeeDto dto){
        return modelMapper.map(dto, Employee.class);

    }

    public EmployeeDto getEmployeeById(long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                //supplier concept of functional Interface.
                ()->new ResourceNotFound(STR."Record not found by Id\{empId}")
        );
        return mapToDto(employee);
    }
}
