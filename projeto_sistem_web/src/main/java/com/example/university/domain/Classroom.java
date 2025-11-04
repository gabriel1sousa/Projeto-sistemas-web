package com.example.university.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "classroom")
public class Classroom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    @OneToMany(mappedBy = "classroom")
    private List<Course> courses = new ArrayList<>();

    public Classroom() {}
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getLocation(){return location;}
    public void setLocation(String location){this.location=location;}
    public List<Course> getCourses(){return courses;}
}
