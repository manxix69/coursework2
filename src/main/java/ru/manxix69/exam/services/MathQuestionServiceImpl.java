package ru.manxix69.exam.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.repository.QuestionRepository;

import java.util.*;
@Service
@Qualifier("mathQuestionServiceImpl")
public class MathQuestionServiceImpl implements MathQuestionService{

    private QuestionRepository questionRepository;

    public MathQuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
    public Question getRandomQuestion(Collection<Question> set) {
        Random randomAction = new Random();
        Random randomInteger = new Random();
        Question question = null;
        int action = randomAction.nextInt(0, 4);
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
            if (q.getQuestion().equals(question)
                    && q.getAnswer().equals(answer)) {
                findQuestion = q;
            }
        }
        return findQuestion;
    }
}
