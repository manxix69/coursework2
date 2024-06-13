package ru.manxix69.exam.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestServiceTests {
    @Autowired
    private TestService testService;

    private QuestionService javaQuestionService;
    private QuestionService mathQuestionService;

    public TestServiceTests(@Qualifier("javaQuestionServiceImpl") QuestionService javaQuestionService
            , @Qualifier("mathQuestionServiceImpl") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }
    @BeforeEach
    public void init() {
        testService = new TestServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Test
    public void initTestJava() {
        testService.createQuestionsJava();
        Assertions.assertNotNull(javaQuestionService.getAll());
    }

    @Test
    public void initTestMath() {
        testService.createQuestionsMath();
        Assertions.assertNotNull(javaQuestionService.getAll());
    }
}
