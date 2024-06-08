package ru.manxix69.exam.controllesrs;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.manxix69.exam.domain.Question;
import ru.manxix69.exam.services.TestService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/test")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public Collection<Question> initializeQuestions() {
        return testService.createQuestions();
    }
}
