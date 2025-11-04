package com.example.university.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "course")
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String title;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments = new ArrayList<>();

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private CourseDetail detail;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

    public Course() {}
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getCode(){return code;}
    public void setCode(String code){this.code=code;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
    public Teacher getTeacher(){return teacher;}
    public void setTeacher(Teacher teacher){this.teacher=teacher;}
    public Classroom getClassroom(){return classroom;}
    public void setClassroom(Classroom classroom){this.classroom=classroom;}
    public List<Assignment> getAssignments(){return assignments;}
    public CourseDetail getDetail(){return detail;}
    public void setDetail(CourseDetail detail){this.detail=detail;}
    public List<Enrollment> getEnrollments(){return enrollments;}
}
