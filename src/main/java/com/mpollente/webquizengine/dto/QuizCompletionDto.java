package com.mpollente.webquizengine.dto;

import java.time.LocalDateTime;

public class QuizCompletionDto {

    private long id;
    private LocalDateTime completedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public String toString() {
        return "QuizCompletionDto{" +
                "id=" + id +
                ", completedAt=" + completedAt +
                '}';
    }
}
