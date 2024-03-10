package ru.manxix69.exam.services;

import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.exceptions.BadRequestException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();
//        System.out.println("Set created");
        if (amount > questionService.getAll().size()) {
//            System.out.println("throw new BadRequestException");
            throw new BadRequestException("запрошено большее количество вопросов, чем хранится в сервисе!");
        }
//        System.out.println("start while");
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion(questions));
        }
        return questions;
    }
}
