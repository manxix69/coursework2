package ru.manxix69.exam.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.services.ExaminerService;
import ru.manxix69.exam.services.QuestionService;

import java.util.Collection;
import java.util.Random;

@SpringBootTest
public class ExaminerServiceUnitTest {
    private QuestionService questionService;
    private ExaminerService examinerService;

    @Autowired
    public ExaminerServiceUnitTest(QuestionService questionService, ExaminerService examinerService) {
        this.questionService = questionService;
        this.examinerService = examinerService;
    }

    @Test
    public void test() {
        int amountOfQuestions = new Random().nextInt(1, 20);
        System.out.println("amountOfQuestions=" + amountOfQuestions);
        System.out.println(" questionService.getAll().size()=" + questionService.getAll().size());
        for (int i = 1; i <= amountOfQuestions; i++) {
            while (questionService.getAll().size() < i) {
                questionService.add(new Question("t".repeat(i), "a".repeat(i)));
                System.out.println("t".repeat(i));
            }
        }

        System.out.println(" questionService.getAll().size()=" + questionService.getAll().size());
        //test
        Collection<Question> questions = examinerService.getQuestions(amountOfQuestions);

        //check
        Assertions.assertEquals(questions.size(), amountOfQuestions);
    }
}