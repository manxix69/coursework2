package ru.manxix69.exam.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.exceptions.QuestionStorageLessThanRequested;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class ExaminerServiceImplTests {
    @Autowired
    private JavaQuestionService javaQuestionService;
//    @Autowired
//    private MathQuestionService mathQuestionService;
    @Autowired
    private ExaminerService examinerService;

    @BeforeEach
    public void setUp() {
        examinerService = new ExaminerServiceImpl(javaQuestionService/*, mathQuestionService*/);
    }

    @Test
    public void getQuestions() {
        Assertions.assertThrows(QuestionStorageLessThanRequested.class, () -> examinerService.getQuestions(10));
        Set<Question> questions = new HashSet<>();
        questions.add(new Question("Вопрос0", "Ответ0"));
        questions.add(new Question("Вопрос1", "Ответ1"));
        questions.add(new Question("Вопрос2", "Ответ2"));

        javaQuestionService.add(new Question("Вопрос0", "Ответ0"));
        javaQuestionService.add(new Question("Вопрос1", "Ответ1"));
        javaQuestionService.add(new Question("Вопрос2", "Ответ2"));

        Assertions.assertEquals(examinerService.getQuestions(1).size(),1);
        Assertions.assertEquals(examinerService.getQuestions(2).size(),2);
    }


}
