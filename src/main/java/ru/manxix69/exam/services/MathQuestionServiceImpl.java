package ru.manxix69.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.manxix69.exam.model.Question;
import ru.manxix69.exam.model.RandomInteger;
import ru.manxix69.exam.repository.QuestionRepository;

import java.util.*;
@Service
@Qualifier("mathQuestionServiceImpl")
public class MathQuestionServiceImpl implements MathQuestionService{
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RandomInteger randomInteger;

    public MathQuestionServiceImpl(QuestionRepository questionRepository, @Qualifier("randomInteger") RandomInteger randomInteger) {
        this.questionRepository = questionRepository;
        this.randomInteger = randomInteger;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questionRepository.addQuestion(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question findQuestion = findQuestion(question, answer);
        if (findQuestion == null) {
            return null;
        } else {
            return remove(findQuestion);
        }
    }

    @Override
    public Question remove(Question question) {
        questionRepository.removeQuestion(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Question question = null;
        int action = randomInteger.nextInt(0, 4);
        int intNumber = randomInteger.nextInt(1, 100);
        switch (action) {
            case 0 -> question = new Question("Сколько будет " + intNumber + "+" + intNumber, (intNumber + intNumber) + "");
            case 1 -> question = new Question("Сколько будет " + intNumber + "-" + intNumber, (intNumber - intNumber) + "");
            case 2 -> question = new Question("Сколько будет " + intNumber + "*" + intNumber, (intNumber * intNumber) + "");
            case 3 -> question = new Question("Сколько будет " + intNumber + "/" + intNumber, (intNumber / intNumber) + "");
        }
        return question;
    }

    @Override
    public Question findQuestion(String question, String answer) {
        Question findQuestion = null;
        for (Question q : questionRepository.getAll()) {
            if (question.equals(q.getQuestion()) && answer.equals(q.getAnswer())) {
                findQuestion = q;
                break;
            }
        }
        return findQuestion;
    }
}
