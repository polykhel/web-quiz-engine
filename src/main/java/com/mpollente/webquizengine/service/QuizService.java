package com.mpollente.webquizengine.service;

import com.mpollente.webquizengine.dto.Feedback;
import com.mpollente.webquizengine.dto.QuizCompletionDto;
import com.mpollente.webquizengine.dto.QuizDto;
import com.mpollente.webquizengine.exception.NotPermittedException;
import com.mpollente.webquizengine.mapper.Mapper;
import com.mpollente.webquizengine.repo.QuizCompletionRepo;
import com.mpollente.webquizengine.repo.QuizRepo;
import com.mpollente.webquizengine.repo.UserRepo;
import com.mpollente.webquizengine.util.Utils;
import com.mpollente.webquizengine.domain.Quiz;
import com.mpollente.webquizengine.domain.QuizCompletion;
import com.mpollente.webquizengine.domain.User;
import com.mpollente.webquizengine.dto.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class QuizService {

    private final QuizRepo quizRepo;
    private final QuizCompletionRepo quizCompletionRepo;
    private final UserRepo userRepo;

    public QuizService(QuizRepo quizRepo, QuizCompletionRepo quizCompletionRepo, UserRepo userRepo) {
        this.quizRepo = quizRepo;
        this.quizCompletionRepo = quizCompletionRepo;
        this.userRepo = userRepo;
    }

    public QuizDto createQuiz(QuizDto quizDto) {
        Quiz quiz = Mapper.toEntity(quizDto);
        return Mapper.toDto(quizRepo.save(quiz));
    }

    public QuizDto getQuiz(long id) {
        return quizRepo.findById(id).map(Mapper::toDto).orElseThrow(EntityNotFoundException::new);
    }

    public Page<QuizDto> getAllQuizzes(Pageable pageable) {
        return quizRepo.findAll(PageRequest.of(pageable.getPageNumber(), 10)).map(Mapper::toDto);
    }

    public Feedback solveQuiz(long id, Answer answer, String username) {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        Quiz quiz = quizRepo.getOne(id);
        if (Utils.isEquals(quiz.getAnswer(), answer.getAnswer())) {
            QuizCompletion completion = quizCompletionRepo.save(new QuizCompletion(quiz, user));
            return new Feedback(true, "Congratulations, you're right!");
        } else {
            return new Feedback(false, "Wrong answer! Please, try again.");
        }
    }

    public void deleteQuiz(long id, String username) {
        QuizDto quiz = getQuiz(id);
        if (quiz.getCreatedBy().equals(username)) {
            quizRepo.deleteById(id);
        } else {
            throw new NotPermittedException();
        }
    }

    public Page<QuizCompletionDto> getQuizCompletionsByUser(String username, Pageable pageable) {
        return quizCompletionRepo.findAllByUserEmailOrderByCompletedAtDesc(username, PageRequest.of(pageable.getPageNumber(), 10)).map(Mapper::toDto);
    }
}
