package ru.manxix69.exam.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.manxix69.exam.domain.Question;

import java.util.Collection;

@SpringBootTest
public class JavaQuestionServiceTests {

    private JavaQuestionService javaQuestionService;
    private Question testQuestion;

    @BeforeEach
    public void setUp() {
        javaQuestionService = new JavaQuestionServiceImpl();
        testQuestion = new Question("Вопрос?", "Ответ.");
    }

    @Test
    public void addQuestion() {
        javaQuestionService.add(testQuestion);
        Assertions.assertEquals(javaQuestionService.getAll().stream().findFirst().get(), testQuestion);
    }

    @Test
    public void addQuestionString() {
        javaQuestionService.add(testQuestion.getQuestion(), testQuestion.getAnswer());
        Assertions.assertEquals(javaQuestionService.getAll().stream().findFirst().get(), testQuestion);
    }

    @Test
    public void removeQuestion() {
        addQuestion();
        Assertions.assertEquals(javaQuestionService.remove(testQuestion), testQuestion);
        Assertions.assertEquals(javaQuestionService.getAll().size(), 0);
    }

    @Test
    public void removeQuestionString() {
        addQuestion();
        Assertions.assertEquals(javaQuestionService.remove(testQuestion.getQuestion(), testQuestion.getAnswer()), testQuestion);
        Assertions.assertEquals(javaQuestionService.getAll().size(), 0);
    }

    @Test
    public void getAll() {
        addQuestion();
        Collection<Question> questionsCollection = javaQuestionService.getAll();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            questionsCollection.remove(testQuestion);
            questionsCollection.add(testQuestion);
                }
        );
        Assertions.assertEquals(javaQuestionService.getAll().size(), 1);
    }
    @Test
    public void findQuestion() {
        javaQuestionService.add(testQuestion);
        Assertions.assertEquals(javaQuestionService.findQuestion(testQuestion.getQuestion(), testQuestion.getAnswer()), testQuestion);
    }

    @Test
    public void getRandomQuestion() {
        javaQuestionService.add(testQuestion);
        Assertions.assertEquals(javaQuestionService.getRandomQuestion(), testQuestion);
    }
}
