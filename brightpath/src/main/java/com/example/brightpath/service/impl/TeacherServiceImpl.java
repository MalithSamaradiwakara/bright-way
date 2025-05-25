package com.example.brightpath.service.impl;

import com.example.brightpath.dto.TeacherRegistrationDTO;
import com.example.brightpath.entity.Login;
import com.example.brightpath.entity.Teacher;
import com.example.brightpath.exception.ResourceNotFoundException;
import com.example.brightpath.repository.LoginRepository;
import com.example.brightpath.repository.TeacherRepository;
import com.example.brightpath.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final LoginRepository loginRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, LoginRepository loginRepository) {
        this.teacherRepository = teacherRepository;
        this.loginRepository = loginRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher registerTeacherWithLogin(TeacherRegistrationDTO dto) {
        Teacher teacher = new Teacher();
        teacher.settName(dto.gettName());
        teacher.settEmail(dto.gettEmail());
        teacher.settDescription(dto.gettDescription());
        teacher.settPhoto(dto.gettPhoto());
        teacher.setQualification(dto.getQualification());

        // Save teacher
        Teacher savedTeacher = teacherRepository.save(teacher);

        // Create login with hashed password
        Login login = new Login();
        login.setTeacher(savedTeacher);
        login.setUsername(dto.getUsername());
        login.setPassword(passwordEncoder.encode(dto.getPassword()));
        login.setUserType(Login.UserType.Teacher);

        loginRepository.save(login);

        return savedTeacher;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher getTeacherById(int id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }

    @Override
    public Teacher updateTeacher(int id, Teacher updatedTeacher) {
        Teacher existingTeacher = getTeacherById(id);
        existingTeacher.settName(updatedTeacher.gettName());
        existingTeacher.settEmail(updatedTeacher.gettEmail());
        existingTeacher.settDescription(updatedTeacher.gettDescription());
        existingTeacher.settPhoto(updatedTeacher.gettPhoto());
        return teacherRepository.save(existingTeacher);
    }

    @Override
    public void deleteTeacher(int id) {
        Teacher teacher = getTeacherById(id);
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher addQualification(int teacherId, String qualification) {
        Teacher teacher = getTeacherById(teacherId);
        teacher.getQualification().add(qualification);
        return teacherRepository.save(teacher);
    }

    @Override
    public void removeQualification(int teacherId, String qualification) {
        Teacher teacher = getTeacherById(teacherId);
        teacher.getQualification().remove(qualification);
        teacherRepository.save(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> getQualificationsByTeacherId(int teacherId) {
        return getTeacherById(teacherId).getQualification();
    }

    @Override
    public Teacher replaceQualifications(int teacherId, Set<String> newQualifications) {
        Teacher teacher = getTeacherById(teacherId);
        teacher.setQualification(newQualifications);
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateQualification(int teacherId, String oldQualification, String newQualification) {
        Teacher teacher = getTeacherById(teacherId);
        Set<String> qualifications = teacher.getQualification();

        if (!qualifications.contains(oldQualification)) {
            throw new IllegalArgumentException("Old qualification not found");
        }

        qualifications.remove(oldQualification);
        qualifications.add(newQualification);

        teacher.setQualification(qualifications);
        return teacherRepository.save(teacher);
    }
}
