package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Question;
import com.example.SkincareProductSales.entity.request.QuestionRequest;
import com.example.SkincareProductSales.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ModelMapper modelMapper;

    public Question createQuestion(QuestionRequest questionRequest) {
        Question question = modelMapper.map(questionRequest, Question.class);
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findQuestionsByIsDeletedFalse();
    }

    public List<Question> getQuestionsIsDeleted() {
        return questionRepository.findQuestionsByIsDeletedTrue();
    }

    public Question getQuestionById(long questionId) {
        Question question = questionRepository.findQuestionById(questionId);
        if (question == null) {
            throw new EntityNotFoundException("Question not found");
        }

        return question;
    }

    public Question updateQuestion(long questionId, QuestionRequest questionRequest) {
        Question question = getQuestionById(questionId);

        question.setQuestionText(questionRequest.getQuestionText());

        return questionRepository.save(question);
    }

    public Question deleteQuestion(long questionId) {
        Question question = questionRepository.findQuestionById(questionId);
        if (question == null) {
            throw new EntityNotFoundException("Question not found");
        }

        question.setDeleted(true);
        return questionRepository.save(question);
    }

}
