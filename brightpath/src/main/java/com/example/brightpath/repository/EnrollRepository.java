package com.example.brightpath.repository;
import com.example.brightpath.entity.Enroll;
import com.example.brightpath.entity.EnrollId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface EnrollRepository extends JpaRepository<Enroll, EnrollId> {
    
    @Query("SELECT e FROM Enroll e WHERE e.student.id = :studentId")
    List<Enroll> findByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT COUNT(e) > 0 FROM Enroll e WHERE e.id.studentId = :studentId AND e.id.courseId = :courseId")
    boolean existsByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);
    
    @Query("SELECT e FROM Enroll e WHERE e.id.courseId = :courseId")
    List<Enroll> findByCourseId(@Param("courseId") Long courseId);
    
    // Find an enrollment by student ID and course ID
    Optional<Enroll> findById_StudentIdAndId_CourseId(Long studentId, Long courseId);
    
    // Find enrollments by status
    List<Enroll> findByStatus(String status);
    
    // Find approved enrollments for a specific student
    @Query("SELECT e FROM Enroll e WHERE e.student.id = :studentId AND e.status = 'Approved'")
    List<Enroll> findApprovedByStudentId(@Param("studentId") Long studentId);
    
    // Get the count of enrollments grouped by status
    @Query("SELECT e.status, COUNT(e) FROM Enroll e GROUP BY e.status")
    List<Object[]> countByStatus();
}