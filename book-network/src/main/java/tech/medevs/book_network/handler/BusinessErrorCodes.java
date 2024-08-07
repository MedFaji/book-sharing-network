package tech.medevs.book_network.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Incorrect credentials"),
    NEW_PASSWORD_DOES_NOT_MATCH(302, BAD_REQUEST, "New password does not match"),
    ACCOUNT_DISABLED(301, FORBIDDEN, "Account is disabled"),
    ACCOUNT_LOCKED(302, FORBIDDEN, "Account is locked"),
    BAD_CREDENTIALS(303, UNAUTHORIZED, "Login or Password is incorrect"),
    ;

    @Getter
    private final int code;
    @Getter
    private final HttpStatus httpStatus;
    @Getter
    private final String description;


    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

}
