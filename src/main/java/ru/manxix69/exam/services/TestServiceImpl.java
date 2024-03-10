package ru.manxix69.exam.services;

import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;

import java.util.Collection;

@Service
public class TestServiceImpl implements TestService {
    QuestionService questionService;

    public TestServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> createQuestions() {
        questionService.add("Дайте определение переменной. Перечислите типы переменных и хранимые в них данные", "ответ");
        questionService.add("Какие условные операторы вы знаете? Дайте краткое определение каждому из них.", "ответ");
        questionService.add("Какие циклы вы знаете, в чем их отличия?", "ответ");
        questionService.add("Какие есть способы создания объекта String? Где он создается?", "ответ");
        questionService.add("Как сравнить две строки в Java и/или отсортировать их?", "ответ");
        questionService.add("Расскажите про String в Java", "ответ");
        questionService.add("Когда применяется цикл while?", "ответ");
        questionService.add("Методы каких типов бывают? Приведите примеры использования каждого типа.", "ответ");
        questionService.add("Что такое массив?", "ответ");
        questionService.add("Дайте определение классу в Java. ", "ответ");
        questionService.add("Когда применяется ключевое слово this?", "ответ");
        questionService.add("В чем разница между == и equals()?", "ответ");
        questionService.add("Для чего применяется метод toString?", "ответ");
        questionService.add("Какие методы называются геттерами? Что они делают?", "ответ");
        questionService.add("Дайте определение сеттерам. Расскажите, для чего они нужны?", "ответ");
        questionService.add("Что такое конструкторы?", "ответ");

        return questionService.getAll();
    }
}
