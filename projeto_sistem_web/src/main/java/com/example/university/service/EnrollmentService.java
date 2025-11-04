package com.example.university.service;

import com.example.university.domain.*;
import com.example.university.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final StudentRepository studentRepo;
    private final com.example.university.repository.CourseRepository courseRepo;
    private final SubmissionRepository submissionRepo;

    public EnrollmentService(EnrollmentRepository enrollmentRepo, StudentRepository studentRepo,
                             com.example.university.repository.CourseRepository courseRepo, SubmissionRepository submissionRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.submissionRepo = submissionRepo;
    }

    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        EnrollmentId eid = new EnrollmentId(studentId, courseId);
        if(enrollmentRepo.existsById(eid)) throw new IllegalStateException("Student already enrolled");

        Enrollment enrollment = new Enrollment();
        enrollment.setId(eid);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollmentRepo.save(enrollment);

        Optional<Assignment> first = course.getAssignments().stream().findFirst();
        if(first.isPresent()) {
            Assignment a = first.get();
            Submission sub = new Submission();
            SubmissionId sid = new SubmissionId(a.getId(), studentId);
            sub.setId(sid);
            sub.setAssignment(a);
            sub.setStudent(student);
            submissionRepo.save(sub);
        }
        return enrollment;
    }

    @Transactional(readOnly = true)
    public Double getAverageGradeInCourse(Long courseId){
        return enrollmentRepo.averageGradeByCourse(courseId);
    }
}
