package ru.manxix69.exam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionStorageLessThanRequested extends RuntimeException {
    public QuestionStorageLessThanRequested() {
    }

    public QuestionStorageLessThanRequested(String message) {
        super(message);
    }

    public QuestionStorageLessThanRequested(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionStorageLessThanRequested(Throwable cause) {
        super(cause);
    }

    public QuestionStorageLessThanRequested(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
