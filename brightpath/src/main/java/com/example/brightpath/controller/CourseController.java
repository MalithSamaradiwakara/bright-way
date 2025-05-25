package com.example.brightpath.controller;

import com.example.brightpath.dto.CourseDTO;
import com.example.brightpath.entity.Course;
import com.example.brightpath.entity.Teacher;
import com.example.brightpath.service.CourseService;
import com.example.brightpath.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private TeacherService teacherService;

    // Get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid course ID: " + id);
        }
    }

    // Create new course with DTO
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO) {
        try {
            // Convert DTO to Entity
            Course course = convertDTOToEntity(courseDTO);
            Course createdCourse = courseService.createCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating course: " + e.getMessage());
        }
    }

    // Update course with DTO
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        try {
            Course course = convertDTOToEntity(courseDTO);
            Course updatedCourse = courseService.updateCourse(id, course);
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating course: " + e.getMessage());
        }
    }

    // Delete course
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found with ID: " + id);
        }
    }
    
    // Helper method to convert DTO to Entity
    private Course convertDTOToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setTitle(courseDTO.getTitle());
        course.setIcon(courseDTO.getIcon());
        course.setDescription(courseDTO.getDescription());
        course.setFullDescription(courseDTO.getFullDescription());
        course.setDuration(courseDTO.getDuration());
        course.setLevel(courseDTO.getLevel());
        course.setPrerequisites(courseDTO.getPrerequisites());
        course.setPrice(courseDTO.getPrice());
        course.setTopics(courseDTO.getTopics());
        
        // Set teacher if provided
        if (courseDTO.getTeacher() != null) {
            Teacher teacher = new Teacher();
            teacher.settId(courseDTO.getTeacher().gettId());
            course.setTeacher(teacher);
        }
        
        return course;
    }
}