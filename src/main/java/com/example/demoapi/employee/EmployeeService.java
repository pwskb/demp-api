package com.example.demoapi.employee;

import com.example.demoapi.exception.BadRequestException;
import com.example.demoapi.exception.ErrorCode;
import com.example.demoapi.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    public List<EmployeeDto> getEmployee() {
        return this.employeeRepository.getEmployee().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto getEmployeeById(long id) {
        Employee employee = this.employeeRepository.getEmployeeById(id).orElseThrow(() -> new NotFoundException(ErrorCode.ERR_NOT_FOUND.code, "employee not found"));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Transactional
    public int saveEmployee(EmployeeDto employeeDto) {
        return this.employeeRepository.saveEmployee(modelMapper.map(employeeDto, Employee.class));
    }

    @Transactional
    public void updateEmployee(EmployeeDto employeeDto) {
        if (this.employeeRepository.updateEmployee(modelMapper.map(employeeDto, Employee.class)) == 0) {
            throw new BadRequestException(ErrorCode.ERR_PUT.code, "employee ID not found");
        }
    }

    @Transactional
    public void updateSomeFieldEmployee(EmployeeDto employeeDto) {
        if (this.employeeRepository.updateSomeFieldEmployee(modelMapper.map(employeeDto, Employee.class)) == 0) {
            throw new BadRequestException(ErrorCode.ERR_PATCH.code, "employee ID not found");
        }
    }

    @Transactional
    public void deleteEmployee(long id) {
        if (this.employeeRepository.deleteEmployee(id) == 0) {
            throw new NotFoundException(ErrorCode.ERR_DELETE.code, "employee ID not found");
        }
//        return this.employeeRepository.deleteEmployee(id);
    }

//    public EmployeeDto addEmployee(EmployeeDto request) throws Exception {
//        try {
//            return this.employeeRepository.addEmployee(request);
//        } catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public Employee updateEmployee(Employee request) throws Exception {
//        try {
//            return this.employeeRepository.updateEmployee(request);
//        } catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public void deleteEmployee(long id) throws Exception {
//        try {
//            this.employeeRepository.deleteEmployee(id);
//        } catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//    }


}
