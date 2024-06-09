package ru.manxix69.exam.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.repository.QuestionRepository;

import java.util.*;

@Service
@Qualifier("javaQuestionServiceImpl")
public class JavaQuestionServiceImpl implements JavaQuestionService {

    private QuestionRepository questionRepository;

    public JavaQuestionServiceImpl(@Qualifier("javaQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(Question question) {
        questionRepository.addQuestion(question);
        return question;
    }

    @Override
    public Question add(String question, String answer) {
        Question addedQuestion = new Question(question, answer);
        questionRepository.addQuestion(addedQuestion);
        return addedQuestion;
    }

    @Override
    public Question remove(Question question) {
        Question removedQuestion = question;
        questionRepository.removeQuestion(question);
        return removedQuestion;
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question remove(String question, String answer) {
        Question findQuesttion = findQuestion(question, answer);
        return remove(findQuesttion);
    }

    public Question findQuestion(String question, String answer) {
        Question findQuestion = null;
        for (Question q : questionRepository.getAll()) {
            if (q.equals(q)) {
                findQuestion = q;
            }
        }
        return findQuestion;
    }


    public Question getRandomQuestion(Collection<Question> set) {
        Random random = new Random();
        Question question = null;
        Collection<Question> questionCollection = new HashSet<>();
        questionCollection.addAll(questionRepository.getAll());
        if (set != null
                && questionCollection != null) {
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
}
