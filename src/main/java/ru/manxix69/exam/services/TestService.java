package ru.manxix69.exam.services;


import ru.manxix69.exam.model.Question;

import java.util.Collection;

public interface TestService {

    Collection<Question> createQuestionsJava();

    Collection<Question> createQuestionsMath();
}
