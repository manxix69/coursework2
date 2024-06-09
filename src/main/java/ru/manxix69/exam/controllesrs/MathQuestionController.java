package ru.manxix69.exam.controllesrs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.services.MathQuestionService;
import ru.manxix69.exam.services.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {

    private final QuestionService questionService;

    MathQuestionController(@Qualifier("mathQuestionServiceImpl") QuestionService questionService ) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping()
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }

    @GetMapping(path = "/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.remove(question, answer);
    }

    @GetMapping(path = "/find")
    public Question findQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.findQuestion(question, answer);
    }
}
