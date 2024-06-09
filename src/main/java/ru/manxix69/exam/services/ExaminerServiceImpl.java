package ru.manxix69.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.exceptions.QuestionStorageLessThanRequested;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private QuestionService javaQuestionService;
//    private QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionServiceImpl") QuestionService javaQuestionService/*,
                               @Qualifier("mathQuestionServiceImpl") QuestionService mathQuestionService*/
    ) {
        this.javaQuestionService = javaQuestionService;
//        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();

        if (amount > javaQuestionService.getAll().size() /*+ mathQuestionService.getAll().size()*/) {
            throw new QuestionStorageLessThanRequested("запрошено большее количество вопросов, чем хранится в сервисе!");
        } else if (amount > (javaQuestionService.getAll().size() /*+ mathQuestionService.getAll().size()*/) / 2) {
            //если запрос больше чем половина всех вопросов по яве и математике то забираем все вопросы и удаляем случайно
            questions.addAll(javaQuestionService.getAll());
//            questions.addAll(mathQuestionService.getAll());

            while (questions.size() > amount) {
                Random random = new Random();
                int randomInt = random.nextInt(0, questions.size());
                int counter = 0;
                for (Question question : questions) {
                    if (counter == randomInt) {
                        questions.remove(question);
                        break;
                    }
                    counter++;
                }
            }
        } else {
            //если запрос меньше чем половина всех вопросов по яве и математике то ищем уже случайные вопросы
            for (int i = 1; i <= amount; i++) {
                while (questions.size() < i) {
                    questions.add(javaQuestionService.getRandomQuestion(questions));
                    /*if (questions.size() < i) {
                        questions.add(mathQuestionService.getRandomQuestion(questions));
                    }*/
                }
            }
        }
        return questions;
    }
}
