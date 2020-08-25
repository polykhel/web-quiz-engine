package com.mpollente.webquizengine.mapper;

import com.mpollente.webquizengine.domain.Quiz;
import com.mpollente.webquizengine.domain.QuizCompletion;
import com.mpollente.webquizengine.domain.User;
import com.mpollente.webquizengine.dto.QuizCompletionDto;
import com.mpollente.webquizengine.dto.QuizDto;
import com.mpollente.webquizengine.dto.UserDto;

public class Mapper {

    public static User toEntity(UserDto dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static Quiz toEntity(QuizDto dto) {
        Quiz entity = new Quiz();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setText(dto.getText());
        entity.setOptions(dto.getOptions());
        entity.setAnswer(dto.getAnswer());
        return entity;
    }

    public static QuizDto toDto(Quiz entity) {
        QuizDto dto = new QuizDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setText(entity.getText());
        dto.setOptions(entity.getOptions());
        dto.setCreatedBy(entity.getCreatedBy());
        return dto;
    }

    public static QuizCompletionDto toDto(QuizCompletion entity) {
        QuizCompletionDto dto = new QuizCompletionDto();
        dto.setId(entity.getQuiz().getId());
        dto.setCompletedAt(entity.getCompletedAt());
        return dto;
    }
}
