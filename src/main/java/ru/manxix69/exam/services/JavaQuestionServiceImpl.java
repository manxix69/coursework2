package ru.manxix69.exam.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.manxix69.exam.exceptions.QuestionRepositoryIsEmptyException;
import ru.manxix69.exam.model.Question;
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
            if (q.getQuestion().equals(question)
                    && q.getAnswer().equals(answer)) {
                findQuestion = q;
            }
        }
        return findQuestion;
    }


    public Question getRandomQuestion() {
        if (questionRepository.getAll().size() == 0) {
            throw new QuestionRepositoryIsEmptyException("В репозитории отсутствуют вопросы для генерации!");
        }
        Random random = new Random();
        Question question = null;

        int counter = 0;
        int randomInt = random.nextInt(0, questionRepository.getAll().size());
        for (Question q : questionRepository.getAll()) {
            if (counter == randomInt) {
                question = q;
                break;
            }
            counter++;
        }
        return question;
    }
}
