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
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.repository.JavaQuestionRepository;
import ru.manxix69.exam.repository.QuestionRepository;

import java.util.Collection;
import java.util.HashSet;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTests {

    @Autowired
    private MathQuestionService mathQuestionService;
//    @Mock
//    private MathQuestionRepository mathQuestionRepository;
    @Mock
    private JavaQuestionRepository javaQuestionRepository;
    @Mock
    QuestionRepository questionRepository;
    private Question testQuestion;
    private Question testQuestion2;

    @BeforeEach
    public void setUp() {
//        mathQuestionService = new MathQuestionServiceImpl(mathQuestionRepository);
        mathQuestionService = new MathQuestionServiceImpl(javaQuestionRepository);
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

    /*@Test
    public void getRandomQuestion() {
        Mockito.when()
        mathQuestionService.getRandomQuestion(new HashSet<>());
    }*/
}
