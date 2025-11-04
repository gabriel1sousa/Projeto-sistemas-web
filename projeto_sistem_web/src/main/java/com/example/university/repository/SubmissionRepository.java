package com.example.university.repository;

import com.example.university.domain.Submission;
import com.example.university.domain.SubmissionId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface SubmissionRepository extends JpaRepository<Submission, SubmissionId> {
    @Query("SELECT AVG(s.score) FROM Submission s WHERE s.assignment.course.id = :courseId")
    Double averageScoreByCourse(@Param("courseId") Long courseId);
}
