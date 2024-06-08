package ru.manxix69.exam.services;


import ru.manxix69.exam.domain.Question;

import java.util.Collection;

public interface TestService {
    Collection<Question> createQuestions();
}
