package ru.manxix69.exam.controllesrs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.manxix69.exam.services.QuestionService;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {

    private final QuestionService questionService;

    MathQuestionController(@Qualifier("mathQuestionServiceImpl") QuestionService questionService ) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/add")
    public HttpStatus addQuestion(@RequestParam String question, @RequestParam String answer) {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }

    @GetMapping()
    public HttpStatus getQuestions() {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }

    @GetMapping(path = "/remove")
    public HttpStatus removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }

    @GetMapping(path = "/find")
    public HttpStatus findQuestion(@RequestParam String question, @RequestParam String answer) {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }
}
