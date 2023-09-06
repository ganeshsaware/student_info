package com.students.info.Controller;

import com.students.info.Entities.Student;
import com.students.info.Service.StudentService;
import com.students.info.payload.StudentDto;
import com.students.info.payload.StudentResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //http://localhost:8080/api/students
    @PostMapping
    public ResponseEntity<?> CreateStudent(@RequestBody StudentDto studentDto){
        StudentDto dto = studentService.saveStudent(studentDto);
        return new ResponseEntity<> (dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/students?pageNo=1&pageSize=3&sortBy=id&sortDir=asc
    @GetMapping
    public StudentResponce listStudent (
            @RequestParam (value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam (value = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam (value= "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam (value = "sortDir", defaultValue = "asc", required = false) String sortDir
        ){
      StudentResponce studentResponce = studentService.getAllStudents(pageNo ,pageSize, sortBy, sortDir);
        return studentResponce;
    }

    //http://localhost:8080/api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> StudentById (@PathVariable ("id") long id){
        StudentDto dto = studentService.getStudentById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateById (
                        @PathVariable ("id") long id,
                        @RequestBody StudentDto studentDto){
        StudentDto dto = studentService.updateById(studentDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById (@PathVariable ("id") long id){
         studentService.deleteStudentById(id);
        return new ResponseEntity<>("Student record deleted", HttpStatus.OK);
    }
}
