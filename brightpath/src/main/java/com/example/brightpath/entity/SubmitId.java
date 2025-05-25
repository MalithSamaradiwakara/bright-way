package com.example.brightpath.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class SubmitId implements Serializable {
    private Integer assignmentId;
    private Integer sId;

    public SubmitId() {}

    public SubmitId(Integer assignmentId, Integer sId) {
        this.assignmentId = assignmentId;
        this.sId = sId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignmentId, sId);
    }
}
