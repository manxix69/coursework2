package ru.manxix69.exam.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.exceptions.QuestionExistsIntoRepositoryExcepton;
import ru.manxix69.exam.exceptions.QuestionNotExistsIntoRepositoryExcepton;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Repository
@Qualifier("mathQuestionRepository")
public class MathQuestionRepository implements QuestionRepository{

    private Set<Question> questions = new HashSet<>();

    @Override
    public Question addQuestion(Question question) {
        if (questions.contains(question)){
            throw new QuestionExistsIntoRepositoryExcepton("Вопрос уже существует в хранилище!");
        } else {
            questions.add(question);
            return question;
        }
    }
    @Override
    public Question removeQuestion(Question question) {
        if (!questions.contains(question)){
            throw new QuestionNotExistsIntoRepositoryExcepton("Вопрос отсутствует в хранилище!");
        } else {
            questions.remove(question);
            return question;
        }
    }
    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }
}
