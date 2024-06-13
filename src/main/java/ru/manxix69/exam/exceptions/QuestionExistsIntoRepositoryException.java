package ru.manxix69.exam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionExistsIntoRepositoryException extends RuntimeException {
    public QuestionExistsIntoRepositoryException(String s) {
    }
}
