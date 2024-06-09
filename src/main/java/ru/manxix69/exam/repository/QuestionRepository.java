package ru.manxix69.exam.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.manxix69.exam.domain.Question;

import java.util.Collection;


public interface QuestionRepository {
    Question addQuestion(Question question);

    Question removeQuestion(Question question);

    Collection<Question> getAll();
}
