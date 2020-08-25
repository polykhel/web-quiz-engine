package com.mpollente.webquizengine.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class QuizCompletion {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Quiz quiz;

    @ManyToOne(optional = false)
    private User user;

    @CreatedDate
    private LocalDateTime completedAt;

    public QuizCompletion() {
    }

    public QuizCompletion(Quiz quiz, User user) {
        this.quiz = quiz;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public String toString() {
        return "QuizCompletion{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", user=" + user +
                ", completedAt=" + completedAt +
                '}';
    }
}
