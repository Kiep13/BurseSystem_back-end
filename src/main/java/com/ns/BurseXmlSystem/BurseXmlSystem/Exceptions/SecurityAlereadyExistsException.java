package com.ns.BurseXmlSystem.BurseXmlSystem.Exceptions;

public class SecurityAlereadyExistsException extends Exception{

    public SecurityAlereadyExistsException() {
        super("Security with such exception is already exists");
    }
}
