package com.student.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentControler {
    private final StudentService studentService;
    @Autowired
    public StudentControler(StudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studentService.getStudentData();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("im in post..");
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentID}")
    public void deleteStudent(@PathVariable("studentID") int id){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        studentService.deleteStudent(id);
    }
    @PutMapping(path= "{studentID}")
    public void updateStudent(
            @PathVariable("studentID") int studentID,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentID, name, email);

    }

}
