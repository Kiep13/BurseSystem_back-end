package com.ns.BurseXmlSystem.BurseXmlSystem.Parsers;

import com.ns.BurseXmlSystem.BurseXmlSystem.Exceptions.WrongXmlFileException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public abstract class Parser {

    public File file;

    public Parser() {
    }

    public Parser(File file) {
        this.file = file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public NodeList getElements(String type, String name) throws WrongXmlFileException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = null;
        try {
            document = builder.parse(file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        if(!isContainDataId(type, document)) {
            throw new WrongXmlFileException();
        }

        NodeList elements = document.getDocumentElement().getElementsByTagName(name);
        return elements;
    }

    public Boolean isContainDataId(String type, Document document) {
        NodeList elements = document.getDocumentElement().getElementsByTagName("data");
        if(elements.getLength() == 0) {
            return false;
        }

        Node row = elements.item(0);
        NamedNodeMap attributes = row.getAttributes();

        return attributes.getNamedItem("id").getNodeValue().compareTo(type) == 0;
    }

    public Integer solveIntegerValue(String stringValue){
        return stringValue.length() == 0 ? null : Integer.parseInt(stringValue);
    }

    public Long solveLongValue(String stringValue){
        return stringValue.length() == 0 ? null : Long.parseLong(stringValue);
    }

    public Double solveDoubleValue(String stringValue){
        return stringValue.length() == 0 ? null : Double.parseDouble(stringValue);
    }

    public Boolean solveBooleanValue(String stringValue){
        if(stringValue.compareTo("1") == 0){
            return true;
        } else if (stringValue.compareTo("0") == 0) {
            return false;
        }
        return null;
    }
}
