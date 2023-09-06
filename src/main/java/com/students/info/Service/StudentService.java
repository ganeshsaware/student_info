package com.students.info.Service;

import com.students.info.Entities.Student;
import com.students.info.payload.StudentDto;
import com.students.info.payload.StudentResponce;

import java.util.List;

public interface StudentService {

     StudentDto saveStudent(StudentDto studentDto);

     StudentResponce getAllStudents(int pageNo , int pageSize, String sortBy, String sortDir);

     StudentDto getStudentById(long id);

     StudentDto updateById(StudentDto studentDto, long id);

     void deleteStudentById(long id);
}
