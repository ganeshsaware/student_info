package com.students.info.Controller;

import com.students.info.Service.ParentService;
import com.students.info.payload.ParentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ParentController {

    private ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }
    //http://localhost:8080/api/students/id/parent
    @PostMapping("/student/{student_id}/parent")
    public ResponseEntity<ParentDto> createComment(
            @PathVariable("student_id") long student_id,
            @RequestBody ParentDto parentDto
    ) {
        ParentDto dto = parentService.saveParent(student_id, parentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
