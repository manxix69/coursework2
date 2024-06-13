package ru.manxix69.exam.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.manxix69.exam.model.Question;
import ru.manxix69.exam.model.RandomInteger;
import ru.manxix69.exam.repository.JavaQuestionRepository;
import ru.manxix69.exam.repository.QuestionRepository;

import java.util.Collection;
import java.util.HashSet;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTests {

    @Autowired
    private MathQuestionService mathQuestionService;
    @MockBean
    private RandomInteger randomInteger;

    @Mock
    private JavaQuestionRepository javaQuestionRepository;
    @Mock
    private QuestionRepository questionRepository;
    private Question testQuestion;
    private Question testQuestion2;

    @BeforeEach
    public void setUp() {
        mathQuestionService = new MathQuestionServiceImpl(javaQuestionRepository, randomInteger);
        testQuestion = new Question("Вопрос?", "Ответ.");
        testQuestion2 = new Question("Вопрос1", "Ответ2");
    }

    @Test
    public void shouldBeAddQuestion() {
        Question question = testQuestion;
        Collection<Question> questions = new HashSet<>();
        questions.add(question);
        mathQuestionService.add(question.getQuestion(), question.getAnswer());

        Mockito.when(javaQuestionRepository.getAll()).thenReturn(questions);
        Assertions.assertNotNull(mathQuestionService.findQuestion(question.getQuestion(), question.getAnswer()));
        Assertions.assertEquals(questions.size(), mathQuestionService.getAll().size());
    }

    @Test
    public void shouldBeRemoveQuestion() {
        Collection<Question> questions = new HashSet<>();
        questions.add(testQuestion);
        questions.add(testQuestion2);
        mathQuestionService.add(testQuestion);
        mathQuestionService.add(testQuestion2);

        Mockito.when(javaQuestionRepository.getAll()).thenReturn(questions);
        Assertions.assertEquals(questions.size(), mathQuestionService.getAll().size());

        Assertions.assertNull(mathQuestionService.remove("task1", "answer1"));
        Assertions.assertEquals(testQuestion, mathQuestionService.remove(testQuestion.getQuestion(), testQuestion.getAnswer()));
    }

    @Test
    public void getRandomQuestion() {
        Question question = null;

        Mockito.when(randomInteger.nextInt(0, 4)).thenReturn(0);
        Mockito.when(randomInteger.nextInt(1, 100)).thenReturn(1);
        question = new Question("Сколько будет " + 1 + "+" + 1, (1 + 1) + "");
        Assertions.assertEquals(question, mathQuestionService.getRandomQuestion());

        Mockito.when(randomInteger.nextInt(0, 4)).thenReturn(1);
        Mockito.when(randomInteger.nextInt(1, 100)).thenReturn(2);
        question = new Question("Сколько будет " + 2 + "-" + 2, (2 - 2) + "");
        Assertions.assertEquals(question, mathQuestionService.getRandomQuestion());

        Mockito.when(randomInteger.nextInt(0, 4)).thenReturn(2);
        Mockito.when(randomInteger.nextInt(1, 100)).thenReturn(3);
        question = new Question("Сколько будет " + 3 + "*" + 3, (3 * 3) + "");
        Assertions.assertEquals(question, mathQuestionService.getRandomQuestion());

        Mockito.when(randomInteger.nextInt(0, 4)).thenReturn(3);
        Mockito.when(randomInteger.nextInt(1, 100)).thenReturn(4);
        question = new Question("Сколько будет " + 4 + "/" + 4, (4 / 4) + "");
        Assertions.assertEquals(question, mathQuestionService.getRandomQuestion());

    }

    @Test
    public void shouldBoNotFindQuestion() {
        questionRepository.addQuestion(testQuestion);
        Assertions.assertNull(mathQuestionService.findQuestion(testQuestion.getQuestion(), testQuestion2.getAnswer()));
        Assertions.assertNull(mathQuestionService.findQuestion(testQuestion2.getQuestion(), testQuestion.getAnswer()));
    }
}
