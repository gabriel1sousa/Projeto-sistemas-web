package com.example.university.controller;

import com.example.university.domain.*;
import com.example.university.repository.EnrollmentRepository;
import com.example.university.service.EnrollmentService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final EnrollmentRepository enrollmentRepo;

    public EnrollmentController(EnrollmentService enrollmentService, EnrollmentRepository enrollmentRepo) {
        this.enrollmentService = enrollmentService;
        this.enrollmentRepo = enrollmentRepo;
    }

    @PostMapping("/enroll")
    public ResponseEntity<Enrollment> enroll(@RequestParam Long studentId, @RequestParam Long courseId){
        Enrollment e = enrollmentService.enrollStudent(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(e);
    }

    @GetMapping("/course/{courseId}/avg-grade")
    public ResponseEntity<Map<String,Object>> avgGrade(@PathVariable Long courseId){
        Double avg = enrollmentService.getAverageGradeInCourse(courseId);
        return ResponseEntity.ok(Map.of("courseId", courseId, "avgGrade", avg));
    }

    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<List<Student>> studentsFiltered(@PathVariable Long courseId,
                                                          @RequestParam(required = false) BigDecimal minGrade,
                                                          @RequestParam(required = false) String name){
        List<Student> students = enrollmentRepo.findStudentsFiltered(courseId, minGrade, name);
        return ResponseEntity.ok(students);
    }
}
