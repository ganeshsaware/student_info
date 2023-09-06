package com.students.info.payload;

import lombok.Data;

@Data
public class StudentDto {

    private Long id;

    private String student_name;

    private String course;

    private Double fees;
}
