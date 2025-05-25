package com.example.brightpath.repository;

import com.example.brightpath.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query("SELECT l FROM Login l WHERE l.username = :username AND l.userType = :userType")
    Login findByUsernameAndUserType(@Param("username") String username, @Param("userType") String userType);

    @Query("SELECT l FROM Login l WHERE l.username = :username")
    Login findByUsername(@Param("username") String username);

    @Query("SELECT l FROM Login l WHERE l.student.id = :studentId")
    Login findByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT l FROM Login l WHERE l.teacher.id = :teacherId")
    Login findByTeacherId(@Param("teacherId") Long teacherId);
    
    @Query("SELECT l FROM Login l WHERE l.admin.id = :adminId")
    Login findByAdminId(@Param("adminId") Long adminId);
}