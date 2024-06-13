package ru.manxix69.exam.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.manxix69.exam.exceptions.QuestionExistsIntoRepositoryException;
import ru.manxix69.exam.exceptions.QuestionNotExistsIntoRepositoryException;
import ru.manxix69.exam.exceptions.QuestionRepositoryIsEmptyException;
import ru.manxix69.exam.model.Question;
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
    public void tryFindQuestionWithOtherAnswerOrQuestion() {
        javaQuestionService.add(testQuestion);
        Assertions.assertNull(javaQuestionService.findQuestion(testQuestion.getQuestion(), testQuestion2.getAnswer() ));
        Assertions.assertNull(javaQuestionService.findQuestion(testQuestion2.getQuestion(), testQuestion.getAnswer() ));
    }

    @Test
    public void getRandomQuestion() {
        Assertions.assertThrows(QuestionRepositoryIsEmptyException.class, ()->javaQuestionService.getRandomQuestion());
        javaQuestionService.add(testQuestion);
        Assertions.assertEquals(javaQuestionService.getRandomQuestion(), testQuestion);
        javaQuestionService.add(testQuestion2);
        Assertions.assertNotNull(javaQuestionService.getRandomQuestion().toString());

    }
    @Test
    public void shouldBeQuestionExistsIntoRepositoryException() {
        javaQuestionService.add(testQuestion);
        Assertions.assertThrows(QuestionExistsIntoRepositoryException.class, ()->javaQuestionService.add(testQuestion));
    }

    @Test
    public void shouldBeQuestionNotExistsIntoRepositoryException() {
        Assertions.assertThrows(QuestionNotExistsIntoRepositoryException.class, ()->javaQuestionService.remove(testQuestion));
    }

}
