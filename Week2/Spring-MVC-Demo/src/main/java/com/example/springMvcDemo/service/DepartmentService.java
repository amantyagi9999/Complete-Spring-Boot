package com.example.springMvcDemo.service;

import com.example.springMvcDemo.dto.DepartmentDTO;
import com.example.springMvcDemo.entity.DepartmentEntity;
import com.example.springMvcDemo.exception.DepartmentNotFoundException;
import com.example.springMvcDemo.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<DepartmentDTO> getDepartmentById(Long id) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));

        return departmentRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> employeeEntities = departmentRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
//        to check if user is admin
//        log something
        DepartmentEntity toSaveEntity = modelMapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(toSaveEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(Long depId, DepartmentDTO departmentDTO) {
        isExistsByDepartmentId(depId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(depId);
        DepartmentEntity savedEmployeeEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedEmployeeEntity, DepartmentDTO.class);
    }

    public boolean deleteDepartmentById(Long depId) {
        isExistsByDepartmentId(depId);
        departmentRepository.deleteById(depId);
        return true;
    }

    public void isExistsByDepartmentId(Long depId) {
        boolean exists = departmentRepository.existsById(depId);
        if(!exists) throw new DepartmentNotFoundException("Employee not found with id: "+depId);
    }

}
