package ru.manxix69.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.repository.MathQuestionRepository;
import ru.manxix69.exam.repository.QuestionRepository;

import java.util.*;
@Service
@Qualifier("mathQuestionServiceImpl")
public class MathQuestionServiceImpl implements MathQuestionService{

    private QuestionRepository questionRepository;

    public MathQuestionServiceImpl(@Qualifier("mathQuestionRepository") QuestionRepository questionRepository) {
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
        Random random = new Random();
        Question question = null;
        Collection<Question> questionCollection = new HashSet<>();
        questionCollection.addAll(questionRepository.getAll());
        if (set != null && questionCollection != null) {
            questionCollection.removeAll(set);
        }
        int counter = 0;
        int randomInt = random.nextInt(0, questionCollection.size());
        for (Question q : questionCollection) {
            if (counter == randomInt) {
                question = q;
                break;
            }
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
