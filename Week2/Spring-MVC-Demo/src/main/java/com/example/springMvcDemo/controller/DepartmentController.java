package com.example.springMvcDemo.controller;

import com.example.springMvcDemo.dto.DepartmentDTO;
import com.example.springMvcDemo.exception.DepartmentNotFoundException;
import com.example.springMvcDemo.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("department/")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{depId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "depId") Long depId) {
        Optional<DepartmentDTO> employeeDTO = departmentService.getDepartmentById(depId);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new DepartmentNotFoundException("Employee not found with id: "+depId));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(@RequestParam(required = false, name = "inputAge") Integer age,
                                                             @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO inputDepartment) {
        DepartmentDTO savedDepartment = departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }


    @PutMapping(path = "/{depId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long depId) {
        return ResponseEntity.ok(departmentService.updateDepartmentById(depId,departmentDTO));
    }

    @DeleteMapping(path = "/{depId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long depId) {
        boolean gotDeleted = departmentService.deleteDepartmentById(depId);
        if (gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}

