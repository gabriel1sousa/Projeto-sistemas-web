package com.example.university.domain;

import jakarta.persistence.*;
import java.time.*;
import java.math.BigDecimal;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    @EmbeddedId
    private EnrollmentId id = new EnrollmentId();

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private java.time.LocalDateTime enrolledAt;
    private java.math.BigDecimal finalGrade;

    public Enrollment() {}
    public EnrollmentId getId(){return id;}
    public void setId(EnrollmentId id){this.id=id;}
    public Student getStudent(){return student;}
    public void setStudent(Student student){this.student=student;}
    public Course getCourse(){return course;}
    public void setCourse(Course course){this.course=course;}
    public java.time.LocalDateTime getEnrolledAt(){return enrolledAt;}
    public void setEnrolledAt(java.time.LocalDateTime enrolledAt){this.enrolledAt=enrolledAt;}
    public java.math.BigDecimal getFinalGrade(){return finalGrade;}
    public void setFinalGrade(java.math.BigDecimal finalGrade){this.finalGrade=finalGrade;}
}
