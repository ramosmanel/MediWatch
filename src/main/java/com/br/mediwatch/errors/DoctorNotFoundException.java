package com.br.mediwatch.errors;

public class DoctorNotFoundException  extends  RuntimeException{
    public DoctorNotFoundException (String message) {
        super(message);
    }
}
