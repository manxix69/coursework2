package ru.manxix69.exam.repository;

import ru.manxix69.exam.model.Question;

import java.util.Collection;


public interface QuestionRepository {
    Question addQuestion(Question question);

    Question removeQuestion(Question question);

    Collection<Question> getAll();
}
