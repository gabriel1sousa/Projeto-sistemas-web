package com.example.university.domain;

import jakarta.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private java.time.LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Submission> submissions = new ArrayList<>();

    public Assignment() {}
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
    public java.time.LocalDateTime getDueDate(){return dueDate;}
    public void setDueDate(java.time.LocalDateTime dueDate){this.dueDate=dueDate;}
    public Course getCourse(){return course;}
    public void setCourse(Course course){this.course=course;}
    public List<Submission> getSubmissions(){return submissions;}
}
