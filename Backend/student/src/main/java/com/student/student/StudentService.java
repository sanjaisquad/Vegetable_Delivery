package com.student.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public List<Student> getStudentData(){
        return studentRepository.findAll();
    }
    public void addNewStudent(Student student){
        if(studentRepository.findByEmail(student.getEmail()).isPresent()){
            throw new IllegalStateException("Email taken");
        }
       studentRepository.save(student);
        System.out.println("lasdkjfsl");
    }
    public void deleteStudent(int studentID){
    boolean exists = studentRepository.existsById(studentID);
        System.out.println(exists);
    if(!exists){
        throw  new IllegalStateException("Student whit id "+ studentID+"does not exists");
    }
    studentRepository.deleteById(studentID);
    }

    @Transactional
    public void updateStudent(int studentId,String name,String email){
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Student with id "+ studentId + "does not exist"));
        System.out.println(name);

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email!=null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if(studentOptional.isPresent()){
            student.setEmail(email);}
        }
        System.out.println("**** updated successfully ****");

    }
}

