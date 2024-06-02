//package com.student.student;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class StudentConfig {
//    @Bean
//    CommandLineRunner CommandLineRunner(StudentRepository repository) {
//        return args -> {
//            Student mariam = new Student(122, "ponni", LocalDate.of(1970, Month.DECEMBER, 6), "crackerponni43@gmail.com");
//            repository.save(mariam);
//        };
//    }
//}
//
