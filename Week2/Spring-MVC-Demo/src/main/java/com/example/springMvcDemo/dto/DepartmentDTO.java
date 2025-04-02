package com.example.springMvcDemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Name of the department cannot be blank")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in the range: [3, 10]")
    private String name;

    @AssertTrue(message = "Department should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

    private Date createdAt;
}
