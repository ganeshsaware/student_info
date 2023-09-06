package com.students.info.Service.Impl;

import com.students.info.Entities.Parent;
import com.students.info.Entities.Student;
import com.students.info.Exceptions.ResourceNotFoundException;
import com.students.info.Service.ParentService;
import com.students.info.payload.ParentDto;
import com.students.info.repository.ParentRepository;
import com.students.info.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class ParentServiceImpl implements ParentService {

    private ParentRepository parentRepo;

    private StudentRepository studentRepository;

    public ParentServiceImpl(ParentRepository parentRepo, StudentRepository studentRepository) {
        this.parentRepo = parentRepo;
        this.studentRepository = studentRepository;
    }

    @Override
    public ParentDto saveParent(Long student_id, ParentDto parentDto) {
        Student student = studentRepository.findById(student_id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + student_id));

        Parent parent = mapToEntity(parentDto);
        parent.setStudent(student);


        Parent save = parentRepo.save(parent);

        return mapToDto(save);

    }


    Parent mapToEntity (ParentDto parentDto){
        Parent parent = new Parent();
        parent.setP_id(parentDto.getP_id());
        parent.setParent_name(parentDto.getParent_name());
        parent.setOccupation(parentDto.getOccupation());
        parent.setCity(parentDto.getCity());

        return parent;
    }

    ParentDto mapToDto (Parent parent){
        ParentDto Dto = new ParentDto();
        Dto.setP_id(parent.getP_id());
        Dto.setParent_name(parent.getParent_name());
        Dto.setOccupation(parent.getOccupation());
        Dto.setCity(parent.getCity());

        return Dto;
    }
}
