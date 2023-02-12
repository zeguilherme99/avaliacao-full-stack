package br.com.nava.backend.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(Integer id) {
        super("User not found. Id: " + id);
    }
}
