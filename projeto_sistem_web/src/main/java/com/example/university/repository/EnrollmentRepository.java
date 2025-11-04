package com.example.university.repository;

import com.example.university.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    @Query("SELECT count(e) FROM Enrollment e WHERE e.course.id = :courseId")
    long countByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT AVG(e.finalGrade) FROM Enrollment e WHERE e.course.id = :courseId AND e.finalGrade IS NOT NULL")
    Double averageGradeByCourse(@Param("courseId") Long courseId);

    @Query("SELECT e.student FROM Enrollment e WHERE e.course.id = :courseId AND ( :minGrade IS NULL OR e.finalGrade >= :minGrade ) AND ( :name IS NULL OR lower(e.student.name) LIKE lower(concat('%',:name,'%')) )")
    List<Student> findStudentsFiltered(@Param("courseId") Long courseId, @Param("minGrade") BigDecimal minGrade, @Param("name") String name);
}
