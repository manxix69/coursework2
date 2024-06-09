package ru.manxix69.exam.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.manxix69.exam.domain.Question;

import java.util.Collection;
import java.util.Random;

@SpringBootTest
public class ExaminerServiceUnitTest {
    @Autowired
    private JavaQuestionService questionService;
    @Autowired
    private ExaminerService examinerService;


    @Test
    public void test() {
        int amountOfQuestions = new Random().nextInt(1, 20);
        for (int i = 1; i <= amountOfQuestions; i++) {
            while (questionService.getAll().size() < i) {
                questionService.add(new Question("t".repeat(i), "a".repeat(i)));
            }
        }
        Collection<Question> questions = examinerService.getQuestions(amountOfQuestions);
        Assertions.assertEquals(questions.size(), amountOfQuestions);
    }
}
