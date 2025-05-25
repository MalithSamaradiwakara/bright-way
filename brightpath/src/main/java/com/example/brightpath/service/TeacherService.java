package com.example.brightpath.service;

import com.example.brightpath.entity.Teacher;
import com.example.brightpath.dto.TeacherRegistrationDTO;

import java.util.List;
import java.util.Set;

public interface TeacherService {
    // Basic CRUD Operations
    Teacher createTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    Teacher getTeacherById(int id);

    Teacher updateTeacher(int id, Teacher teacher);

    void deleteTeacher(int id);

    // Qualification Management
    Teacher addQualification(int teacherId, String qualification);

    Teacher replaceQualifications(int teacherId, Set<String> newQualifications);

    void removeQualification(int teacherId, String qualification);

    Teacher updateQualification(int teacherId, String oldQualification, String newQualification);

    Set<String> getQualificationsByTeacherId(int teacherId);

    Teacher registerTeacherWithLogin(TeacherRegistrationDTO dto);
}
