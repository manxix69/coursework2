package ru.manxix69.exam.services;

import ru.manxix69.exam.domain.Question;

import java.util.ArrayList;
import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
