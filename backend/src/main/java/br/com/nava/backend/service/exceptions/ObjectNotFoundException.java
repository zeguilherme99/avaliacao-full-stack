package br.com.nava.backend.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String type,Integer id) {
        super(type + " not found. Id: " + id);
    }
}
