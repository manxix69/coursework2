package ru.manxix69.exam.services;

import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.exceptions.QuestionStorageLessThanRequested;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();
        if (amount > questionService.getAll().size()) {
            throw new QuestionStorageLessThanRequested("запрошено большее количество вопросов, чем хранится в сервисе!");
        }
        for (int i = 1; i <= amount; i++) {
            questions.add(questionService.getRandomQuestion(questions));
        }
        return questions;
    }
}
