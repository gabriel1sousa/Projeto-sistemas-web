package com.example.university.repository;

import com.example.university.domain.Course;
import com.example.university.domain.Enrollment;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c.id AS courseId, c.title AS title, COUNT(e) AS studentCount, AVG(e.finalGrade) AS avgGrade " +
           "FROM Course c LEFT JOIN c.enrollments e GROUP BY c.id, c.title")
    List<Object[]> summaryAllCourses();
}
