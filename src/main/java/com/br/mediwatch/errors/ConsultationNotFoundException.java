package com.br.mediwatch.errors;

public class ConsultationNotFoundException extends RuntimeException{
    public ConsultationNotFoundException (String message) {
        super(message);
    }
}
