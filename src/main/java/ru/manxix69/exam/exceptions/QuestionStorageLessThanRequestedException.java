package ru.manxix69.exam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionStorageLessThanRequestedException extends RuntimeException {
    public QuestionStorageLessThanRequestedException(String message) {
        super(message);
    }
}
