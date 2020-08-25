package com.mpollente.webquizengine.repo;

import com.mpollente.webquizengine.domain.QuizCompletion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletionRepo extends JpaRepository<QuizCompletion, Long> {
    Page<QuizCompletion> findAllByUserEmailOrderByCompletedAtDesc(String username, Pageable pageable);
}
