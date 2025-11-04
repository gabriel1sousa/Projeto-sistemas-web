package com.example.university.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubmissionId implements Serializable {
    private Long assignmentId;
    private Long studentId;

    public SubmissionId(){}
    public SubmissionId(Long assignmentId, Long studentId){this.assignmentId=assignmentId;this.studentId=studentId;}
    public Long getAssignmentId(){return assignmentId;}
    public void setAssignmentId(Long assignmentId){this.assignmentId=assignmentId;}
    public Long getStudentId(){return studentId;}
    public void setStudentId(Long studentId){this.studentId=studentId;}
    @Override public boolean equals(Object o){ if(this==o) return true; if(o==null||getClass()!=o.getClass()) return false; SubmissionId that=(SubmissionId)o; return Objects.equals(assignmentId,that.assignmentId)&&Objects.equals(studentId,that.studentId);}
    @Override public int hashCode(){ return Objects.hash(assignmentId, studentId); }
}
