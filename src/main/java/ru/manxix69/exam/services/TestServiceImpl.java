package ru.manxix69.exam.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;

import java.util.Collection;

@Service
public class TestServiceImpl implements TestService {
    private QuestionService javaQuestionService;
    private QuestionService mathQuestionService;

    public TestServiceImpl( @Qualifier("javaQuestionServiceImpl") QuestionService javaQuestionService
                            ,@Qualifier("mathQuestionServiceImpl") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> createQuestionsJava() {
        javaQuestionService.add("Дайте определение переменной. Перечислите типы переменных и хранимые в них данные", "ответ");
        javaQuestionService.add("Какие условные операторы вы знаете? Дайте краткое определение каждому из них.", "ответ");
        javaQuestionService.add("Какие циклы вы знаете, в чем их отличия?", "ответ");
        javaQuestionService.add("Какие есть способы создания объекта String? Где он создается?", "ответ");
        javaQuestionService.add("Как сравнить две строки в Java и/или отсортировать их?", "ответ");
        javaQuestionService.add("Расскажите про String в Java", "ответ");
        javaQuestionService.add("Когда применяется цикл while?", "ответ");
        javaQuestionService.add("Методы каких типов бывают? Приведите примеры использования каждого типа.", "ответ");
        javaQuestionService.add("Что такое массив?", "ответ");
        javaQuestionService.add("Дайте определение классу в Java. ", "ответ");
        javaQuestionService.add("Когда применяется ключевое слово this?", "ответ");
        javaQuestionService.add("В чем разница между == и equals()?", "ответ");
        javaQuestionService.add("Для чего применяется метод toString?", "ответ");
        javaQuestionService.add("Какие методы называются геттерами? Что они делают?", "ответ");
        javaQuestionService.add("Дайте определение сеттерам. Расскажите, для чего они нужны?", "ответ");
        javaQuestionService.add("Что такое конструкторы?", "ответ");

        return javaQuestionService.getAll();
    }

    @Override
    public Collection<Question> createQuestionsMath() {
        javaQuestionService.add("Сколько будет 1+1", "2");
        javaQuestionService.add("Сколько будет 1*1", "1");
        javaQuestionService.add("Сколько будет 1/1", "1");
        javaQuestionService.add("Сколько будет 1-1", "0");

        return javaQuestionService.getAll();
    }
}
