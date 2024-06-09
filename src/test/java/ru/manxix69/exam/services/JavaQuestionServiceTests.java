package ru.manxix69.exam.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.repository.JavaQuestionRepository;

import java.util.Collection;
import java.util.HashSet;

@SpringBootTest
public class JavaQuestionServiceTests {

    @Autowired
    private JavaQuestionService javaQuestionService;
    @Autowired
    private JavaQuestionRepository javaQuestionRepository;

    private Question testQuestion;
    private Question testQuestion2;

    @BeforeEach
    public void setUp() {
        javaQuestionRepository = new JavaQuestionRepository();
        javaQuestionService = new JavaQuestionServiceImpl(javaQuestionRepository);
        testQuestion = new Question("Вопрос?", "Ответ.");
        testQuestion2 = new Question("Вопрос1", "Ответ2");
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
        Assertions.assertEquals(javaQuestionService.getRandomQuestion(null), testQuestion);
    }
    @Test
    public void getRandomQuestionOne() {
        javaQuestionService.add(testQuestion);
        javaQuestionService.add(testQuestion2);

        Collection<Question> set = new HashSet<Question>();
        set.add(testQuestion2);

        Assertions.assertEquals(javaQuestionService.getRandomQuestion(set), testQuestion);
    }
    @Test
    public void getRandomQuestionAll() {
        javaQuestionService.add(testQuestion);
        javaQuestionService.add(testQuestion2);

        Assertions.assertThrows(IllegalArgumentException.class, ()->javaQuestionService.getRandomQuestion(javaQuestionService.getAll()));
    }
}
