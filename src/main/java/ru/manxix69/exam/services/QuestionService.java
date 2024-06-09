package ru.manxix69.exam.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import ru.manxix69.exam.domain.Question;

import java.util.Collection;
import java.util.Set;
@Primary
public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion(Collection<Question> questionSet);

    Question findQuestion(String question, String answer);

    Question remove(String question, String answer);
}
