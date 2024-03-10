package ru.manxix69.exam.services;

import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;

import java.util.*;

@Service
public class JavaQuestionServiceImpl implements QuestionService {

    private Set<Question> questions = new HashSet<>();

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question add(String question, String answer) {
        Question addedQuestion = new Question(question, answer);
        questions.add(addedQuestion);
        return addedQuestion;
    }

    @Override
    public Question remove(Question question) {
        Question removedQuestion = question;
        questions.remove(question);
        return removedQuestion;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question remove(String question, String answer) {
        Question findQuesttion = findQuestion(question, answer);
        return remove(findQuesttion);
    }

    public Question findQuestion(String question, String answer) {
        Question findQuestion = null;
        for (Question q : questions) {
            if (q.getQuestion().equals(question) && q.getAnswer().equals(answer)) {
                findQuestion = q;
            }
        }
        return findQuestion;
    }


    public Question getRandomQuestion(Collection<Question> exceptList) {
        Random random = new Random();
        Question question = null;
        Collection<Question> questionCollection = new HashSet<>();
        questionCollection.addAll(questions);

        if (exceptList != null) {
            questionCollection.removeAll(exceptList);
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
