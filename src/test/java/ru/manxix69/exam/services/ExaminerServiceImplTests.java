package ru.manxix69.exam.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.exceptions.QuestionStorageLessThanRequested;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTests {
    @Mock
    private QuestionService questionService;
    private ExaminerService examinerService;

    @BeforeEach
    public void setUp() {
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    public void getQuestions() {
        Assertions.assertThrows(QuestionStorageLessThanRequested.class, () -> examinerService.getQuestions(10));
        Set<Question> questions = new HashSet<>();
        questions.add(new Question("Вопрос0", "Ответ0"));
        questions.add(new Question("Вопрос1", "Ответ1"));
        questions.add(new Question("Вопрос2", "Ответ2"));

        Mockito.when(questionService.getAll()).thenReturn(questions);
        Assertions.assertEquals(examinerService.getQuestions(1).size(),1);
    }
}
