package com.example.brightpath.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrollId implements Serializable {

    @Column(name = "S_ID", columnDefinition = "BIGINT")
    private Long studentId;

    @Column(name = "C_ID", columnDefinition = "BIGINT")
    private Long courseId;

    // Default constructor for JPA
    public EnrollId() {
    }

    public EnrollId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EnrollId enrollId = (EnrollId) o;
        return Objects.equals(studentId, enrollId.studentId) &&
                Objects.equals(courseId, enrollId.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}