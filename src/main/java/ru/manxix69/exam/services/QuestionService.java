package ru.manxix69.exam.services;

import ru.manxix69.exam.domain.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

    Question findQuestion(String question, String answer);

    Question remove(String question, String answer);
}
