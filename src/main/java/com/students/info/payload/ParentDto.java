package com.students.info.payload;

import lombok.Data;

@Data
public class ParentDto {
    private Long p_id;

    private String parent_name;

    private String occupation;

    private String city;
}
