package de.be.aff.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MyErrorMessage {
    @Getter
    private final String url;
    @Getter
    private final String exceptionMessage;

}