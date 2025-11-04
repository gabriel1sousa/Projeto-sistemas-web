package com.example.university.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "course_detail")
public class CourseDetail {
    @Id
    private Long courseId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(columnDefinition = "TEXT")
    private String syllabus;

    private Integer credits;

    public CourseDetail() {}
    public Long getCourseId(){return courseId;}
    public void setCourseId(Long courseId){this.courseId=courseId;}
    public Course getCourse(){return course;}
    public void setCourse(Course course){this.course=course;}
    public String getSyllabus(){return syllabus;}
    public void setSyllabus(String syllabus){this.syllabus=syllabus;}
    public Integer getCredits(){return credits;}
    public void setCredits(Integer credits){this.credits=credits;}
}
