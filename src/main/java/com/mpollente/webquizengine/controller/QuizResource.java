package com.mpollente.webquizengine.controller;

import com.mpollente.webquizengine.dto.QuizDto;
import com.mpollente.webquizengine.dto.Answer;
import com.mpollente.webquizengine.dto.Feedback;
import com.mpollente.webquizengine.dto.QuizCompletionDto;
import com.mpollente.webquizengine.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/quizzes")
public class QuizResource {

    private final QuizService quizService;

    public QuizResource(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody QuizDto quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuiz(@PathVariable long id) {
        return ResponseEntity.ok(quizService.getQuiz(id));
    }

    @GetMapping
    public ResponseEntity<Page<QuizDto>> getQuizzes(Pageable pageable) {
        return ResponseEntity.ok(quizService.getAllQuizzes(pageable));
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<Feedback> solveQuiz(@PathVariable long id, @RequestBody Answer answer, Principal principal) {
        return ResponseEntity.ok(quizService.solveQuiz(id, answer, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable long id, Principal principal) {
        quizService.deleteQuiz(id, principal.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<QuizCompletionDto>> getCompletedQuizzes(Principal principal, Pageable pageable) {
        return ResponseEntity.ok(quizService.getQuizCompletionsByUser(principal.getName(), pageable));
    }
}
