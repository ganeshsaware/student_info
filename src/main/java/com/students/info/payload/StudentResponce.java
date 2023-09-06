package com.students.info.payload;

import lombok.Data;

import java.util.List;
@Data
public class StudentResponce {
    private List<StudentDto> content;
    private int pageNo;
    private int pageSize;
    private int totalElement;
    private long totalPages;
    private boolean isLast;
}
