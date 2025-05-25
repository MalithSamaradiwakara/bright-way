package com.example.brightpath.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Central configuration for all API endpoints in the application.
 * This class serves as documentation for all available endpoints.
 */
@Configuration
public class ApiConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://frontendmalith-ikrrbt8jb-malithsamaradiwakaras-projects.vercel.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders(
                        "Authorization",
                        "Content-Type",
                        "X-Requested-With",
                        "Accept",
                        "Origin",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders(
                        "Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * API Endpoints Documentation:
     * 
     * Authentication Endpoints (/api/auth):
     * - POST /api/auth/login - User login
     * - GET /api/auth/login/{id} - Get login details by ID
     * 
     * Student Endpoints (/students):
     * - POST /students/register - Register new student
     * - GET /students - Get all students
     * - GET /students/{id} - Get student by ID
     * - GET /students/profile/{id} - Get student profile
     * - PUT /students/{id} - Update student
     * - DELETE /students/{id} - Delete student
     * 
     * Teacher Endpoints (/teacher):
     * - POST /teacher - Create teacher
     * - PUT /teacher/{id} - Update teacher
     * - DELETE /teacher/{id} - Delete teacher
     * - POST /teacher/{teacherId}/qualifications - Add qualification
     * - DELETE /teacher/{teacherId}/qualifications - Remove qualification
     * - GET /teacher/{teacherId}/qualifications - Get qualifications
     * - PUT /teacher/{teacherId}/qualifications/full - Update all qualifications
     * 
     * Course Endpoints (/api/courses):
     * - GET /api/courses - Get all courses
     * - GET /api/courses/{id} - Get course by ID
     * - POST /api/courses - Create course
     * - PUT /api/courses/{id} - Update course
     * - DELETE /api/courses/{id} - Delete course
     * 
     * Assignment Endpoints (/api/assignments):
     * - POST /api/assignments - Create assignment
     * - GET /api/assignments/{id} - Get assignment by ID
     * - GET /api/assignments - Get all assignments
     * - PUT /api/assignments/{id} - Update assignment
     * - DELETE /api/assignments/{id} - Delete assignment
     * - GET /api/assignments/student/{studentId} - Get assignments by student
     * - GET /api/assignments/teacher/{teacherId} - Get assignments by teacher
     * - GET /api/assignments/course/{courseId} - Get assignments by course
     * 
     * Quiz Endpoints (/api/quizzes):
     * - POST /api/quizzes - Create quiz
     * - GET /api/quizzes/{id} - Get quiz by ID
     * - GET /api/quizzes - Get all quizzes
     * - PUT /api/quizzes/{id} - Update quiz
     * - DELETE /api/quizzes/{id} - Delete quiz
     * 
     * Question Endpoints (/api/questions):
     * - POST /api/questions - Create question
     * - GET /api/questions/{id} - Get question by ID
     * - GET /api/questions - Get all questions
     * - PUT /api/questions/{id} - Update question
     * - DELETE /api/questions/{id} - Delete question
     * - GET /api/questions/quiz/{quizId} - Get questions by quiz
     * 
     * Attempt Endpoints (/api/attempts):
     * - POST /api/attempts - Create attempt
     * - PUT /api/attempts/quiz/{quizId}/student/{studentId} - Update attempt
     * - GET /api/attempts - Get all attempts
     * - GET /api/attempts/quiz/{quizId} - Get attempts by quiz
     * - GET /api/attempts/student/{studentId} - Get attempts by student
     * - DELETE /api/attempts/{quizId} - Delete attempt
     * 
     * Enrollment Endpoints (/api/enroll):
     * - POST /api/enroll - Create enrollment
     * - GET /api/enroll/student/{studentId} - Get student enrollments
     * - GET /api/enroll/{studentId}/{courseId} - Get specific enrollment
     * - GET /api/enroll/pending - Get pending enrollments
     * - GET /api/enroll/all - Get all enrollments
     * - PUT /api/enroll/approve/{studentId}/{courseId} - Approve enrollment
     * - PUT /api/enroll/reject/{studentId}/{courseId} - Reject enrollment
     * 
     * Submission Endpoints (/api/submissions):
     * - POST /api/submissions - Create submission
     * - PUT /api/submissions/assignment/{assignmentId}/student/{studentId} - Update
     * submission
     * - GET /api/submissions - Get all submissions
     * - GET /api/submissions/student/{studentId} - Get submissions by student
     * - GET /api/submissions/assignment/{assignmentId} - Get submissions by
     * assignment
     * - DELETE /api/submissions/{id} - Delete submission
     */
}