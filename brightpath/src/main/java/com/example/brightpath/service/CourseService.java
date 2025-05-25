package com.example.brightpath.service;

import com.example.brightpath.entity.Course;
import com.example.brightpath.entity.Teacher;
import com.example.brightpath.repository.CourseRepository;
import com.example.brightpath.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private TeacherRepository teacherRepository;

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get course by ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Create new course
    @Transactional
    public Course createCourse(Course course) {
        // Validate and set teacher
        if (course.getTeacher() != null && course.getTeacher().gettId() != 0) {
            Optional<Teacher> teacher = teacherRepository.findById(course.getTeacher().gettId());
            if (teacher.isPresent()) {
                course.setTeacher(teacher.get());
            } else {
                throw new RuntimeException("Teacher not found with id: " + course.getTeacher().gettId());
            }
        } else {
            throw new RuntimeException("Teacher is required for creating a course");
        }
        
        // Set default active status
        course.setActive(true);
        
        return courseRepository.save(course);
    }

    // Update course
    @Transactional
    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setTitle(updatedCourse.getTitle());
            course.setIcon(updatedCourse.getIcon());
            course.setDescription(updatedCourse.getDescription());
            course.setFullDescription(updatedCourse.getFullDescription());
            course.setDuration(updatedCourse.getDuration());
            course.setLevel(updatedCourse.getLevel());
            course.setPrerequisites(updatedCourse.getPrerequisites());
            course.setTopics(updatedCourse.getTopics());
            course.setPrice(updatedCourse.getPrice());
            
            // Update teacher if provided
            if (updatedCourse.getTeacher() != null && updatedCourse.getTeacher().gettId() != 0) {
                Optional<Teacher> teacher = teacherRepository.findById(updatedCourse.getTeacher().gettId());
                if (teacher.isPresent()) {
                    course.setTeacher(teacher.get());
                } else {
                    throw new RuntimeException("Teacher not found with id: " + updatedCourse.getTeacher().gettId());
                }
            }
            
            return courseRepository.save(course);
        }).orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    // Delete course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}