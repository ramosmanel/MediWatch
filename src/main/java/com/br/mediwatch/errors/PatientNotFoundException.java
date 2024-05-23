package com.br.mediwatch.errors;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException (String message) {
        super(message);
    }
}
