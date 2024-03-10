package ru.manxix69.exam.services;

import ru.manxix69.exam.domain.Question;

import java.util.Collection;

public interface QuestionService{
    Question add(Question question);
    Question add(String question, String answer);
    Question remove(Question question);

    Question findQuestion(String question, String answer);
    Collection<Question> getAll();

    Question getRandomQuestion(Collection<Question> exceptList);
    Question remove(String question, String answer);
}
