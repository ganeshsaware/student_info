package com.students.info.Service.Impl;

import com.students.info.Entities.Student;
import com.students.info.Exceptions.ResourceNotFoundException;
import com.students.info.Service.StudentService;
import com.students.info.payload.StudentDto;
import com.students.info.payload.StudentResponce;
import com.students.info.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {

       Student student = mapToEntity(studentDto);
        studentRepository.save(student);

        return mapToDto(student);
    }

    @Override
    public StudentResponce getAllStudents(int pageNo ,int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Student> content = studentRepository.findAll(pageable);

        List<Student> students = content.getContent();

        List<StudentDto> dtos = students.stream().map(student -> mapToDto(student)).collect(Collectors.toList());

        StudentResponce studentResponce = new StudentResponce();
        studentResponce.setContent(dtos);
        studentResponce.setPageNo(content.getNumber());
        studentResponce.setPageSize(content.getSize());
        studentResponce.setTotalPages(content.getTotalPages());
        studentResponce.setTotalElement((int) content.getTotalElements());

        return studentResponce;
    }

    @Override
    public StudentDto getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Student not found with id :" +id));
        StudentDto dto = mapToDto(student);
        return dto;
    }

    @Override
    public StudentDto updateById(StudentDto studentDto, long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Student not found with the id " +id));

        student.setStudent_name(studentDto.getStudent_name());
        student.setCourse(studentDto.getCourse());
        student.setFees(studentDto.getFees());

        Student updatedStudent = studentRepository.save(student);

        StudentDto dto = mapToDto(updatedStudent);
        return dto;
    }

    @Override
    public void deleteStudentById(long id) {
       Student student = studentRepository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("Student not found with the id :"+id));
        studentRepository.deleteById(id);
    }


    Student mapToEntity (StudentDto studentDto){
        Student student = new Student();
        student.setStudent_name(studentDto.getStudent_name());
        student.setCourse(studentDto.getCourse());
        student.setFees(studentDto.getFees());
        return student;
    }
    StudentDto mapToDto (Student student){
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setStudent_name(student.getStudent_name());
        dto.setCourse(student.getCourse());
        dto.setFees(student.getFees());
        return dto;
    }
}
