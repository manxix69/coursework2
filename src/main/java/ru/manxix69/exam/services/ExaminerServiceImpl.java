package ru.manxix69.exam.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.manxix69.exam.model.Question;
import ru.manxix69.exam.exceptions.QuestionStorageLessThanRequestedException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private QuestionService javaQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionServiceImpl") QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();

        if (amount > javaQuestionService.getAll().size()) {
            throw new QuestionStorageLessThanRequestedException("запрошено большее количество вопросов, чем хранится в сервисе!");
        } else {
            for (int i = 1; i <= amount; i++) {
                while (questions.size() < i) {
                    questions.add(javaQuestionService.getRandomQuestion());
                }
            }
        }
        return questions;
    }
}
