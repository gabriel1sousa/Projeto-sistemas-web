package com.example.university.domain;

import jakarta.persistence.*;
import java.time.*;
import java.math.BigDecimal;

@Entity
@Table(name = "submission")
public class Submission {
    @EmbeddedId
    private SubmissionId id = new SubmissionId();

    @ManyToOne
    @MapsId("assignmentId")
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    private java.time.LocalDateTime submittedAt;
    private java.math.BigDecimal score;

    public Submission() {}
    public SubmissionId getId(){return id;}
    public void setId(SubmissionId id){this.id=id;}
    public Assignment getAssignment(){return assignment;}
    public void setAssignment(Assignment assignment){this.assignment=assignment;}
    public Student getStudent(){return student;}
    public void setStudent(Student student){this.student=student;}
    public java.time.LocalDateTime getSubmittedAt(){return submittedAt;}
    public void setSubmittedAt(java.time.LocalDateTime submittedAt){this.submittedAt=submittedAt;}
    public java.math.BigDecimal getScore(){return score;}
    public void setScore(java.math.BigDecimal score){this.score=score;}
}
