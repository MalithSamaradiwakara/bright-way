package com.example.brightpath.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Embeddable
public class AttemptId implements Serializable {
    private Integer quizId;
    private Integer sId;

    public AttemptId(Integer quizId, Integer sId) {
        this.quizId = quizId;
        this.sId = sId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, sId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AttemptId other = (AttemptId) obj;
        return Objects.equals(quizId, other.quizId) && Objects.equals(sId, other.sId);
    }
}
