package com.example.demoapi.employee;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getEmployee() {
        return ResponseEntity.ok(this.employeeService.getEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long id) throws Exception {
        log.info("id : {}", id);
        return ResponseEntity.ok(this.employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("save EmployeeDto employeeDto : {}", employeeDto);
        this.employeeService.saveEmployee(employeeDto);
        return ResponseEntity.created(null).build();
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("updateEmployee : {}", employeeDto);
        this.employeeService.updateEmployee(employeeDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<?> updateSomeFieldEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("updateEmployee : {}", employeeDto);
        this.employeeService.updateSomeFieldEmployee(employeeDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        log.info("deleteEmployee ID : {}", id);
        this.employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/add")
//    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto request) throws Exception {
//        log.info("request : {}", request);
//        return ResponseEntity.ok(this.employeeService.addEmployee(request));
//    }
//
//    @PostMapping("/update")
//    public ResponseEntity<?> updateEmployee(@RequestBody Employee request) throws Exception {
//        log.info("request : {}", request);
//        return ResponseEntity.ok(this.employeeService.updateEmployee(request));
//    }
//
//    @PostMapping("/delete")
//    public ResponseEntity<?> deleteEmployee(@RequestBody Employee request) throws Exception {
//        log.info("request : {}", request);
//        this.employeeService.deleteEmployee(request.getId());
//        return ResponseEntity.ok(null);
//    }


}
