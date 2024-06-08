package ru.manxix69.exam.services;

import ru.manxix69.exam.domain.Question;

import java.util.*;

public class MathQuestionServiceImpl implements MathQuestionService{
    private Set<Question> questions = new HashSet<>();
    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
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
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion(Collection<Question> set) {
        Random random = new Random();
        Question question = null;
        Collection<Question> questionCollection = new HashSet<>();
        questionCollection.addAll(questions);
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
        for (Question q : questions) {
            if (q.getQuestion().equals(question) && q.getAnswer().equals(answer)) {
                findQuestion = q;
            }
        }
        return findQuestion;
    }
}
