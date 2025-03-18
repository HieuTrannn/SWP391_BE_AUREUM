package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Answer;
import com.example.SkincareProductSales.entity.Question;
import com.example.SkincareProductSales.entity.Skin;
import com.example.SkincareProductSales.entity.request.AnswerRequest;
import com.example.SkincareProductSales.repository.AnswerRepository;
import com.example.SkincareProductSales.repository.QuestionRepository;
import com.example.SkincareProductSales.repository.SkinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SkinRepository skinRepository;

    public Answer getAnswerById(long answerId) {
        Answer answer = answerRepository.findAnswerById(answerId);
        if (answer == null) {
            throw new EntityNotFoundException("Answer not found");
        }
        return answer;
    }

    public Answer createAnswer(AnswerRequest answerRequest) {
        Answer answer = modelMapper.map(answerRequest, Answer.class);

        Question question = questionRepository.findQuestionById(answerRequest.getQuestionId());
        Skin skin = skinRepository.findSkinById(answerRequest.getSkinId());

        if (question == null) {
            throw new NullPointerException("Question not found");
        } else if (skin == null) {
            throw new NullPointerException("Skin not found");
        }

        answer.setQuestion(question);
        answer.setSkin(skin);
        return answerRepository.save(answer);
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAnswersByIsDeletedFalse();
    }

    public List<Answer> getAnswersIsDeleted() {
        return answerRepository.findAnswersByIsDeletedTrue();
    }

    public Answer updateAnswer(long answerId, AnswerRequest answerRequest) {
        Answer answer = getAnswerById(answerId);

        answer.setAnswerText(answerRequest.getAnswerText());

        Question question = questionRepository.findQuestionById(answerRequest.getQuestionId());
        Skin skin = skinRepository.findSkinById(answerRequest.getSkinId());

        answer.setQuestion(question);
        answer.setSkin(skin);
        return answerRepository.save(answer);
    }

    public Answer deleteAnswer(long answerId) {
        Answer answer = answerRepository.findAnswerById(answerId);
        if (answer == null) {
            throw new EntityNotFoundException("Answer not found");
        }
        answer.setDeleted(true);
        return answerRepository.save(answer);
    }
}
