package ru.manxix69.exam.services;

import org.springframework.stereotype.Service;
import ru.manxix69.exam.domain.Question;

import java.util.*;

@Service
public class JavaQuestionServiceImpl implements JavaQuestionService {

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
        return Collections.unmodifiableCollection(questions);
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


    public Question getRandomQuestion() {
        Random random = new Random();
        Question question = null;
        int counter = 0;
        int randomInt = random.nextInt(0, questions.size());
        for (Question q : questions) {
            if (counter == randomInt) {
                question = q;
                break;
            }
        }
        return question;
    }
}
