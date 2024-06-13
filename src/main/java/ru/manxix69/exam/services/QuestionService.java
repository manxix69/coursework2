package ru.manxix69.exam.services;

import org.springframework.context.annotation.Primary;
import ru.manxix69.exam.model.Question;

import java.util.Collection;

@Primary
public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

    Question findQuestion(String question, String answer);

    Question remove(String question, String answer);
}
