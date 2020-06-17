package com.ns.BurseXmlSystem.BurseXmlSystem.Exceptions;

public class WrongXmlFileException extends Exception {

    public WrongXmlFileException() {
        super("The uploaded XML file does not meet the requirements");
    }
}
