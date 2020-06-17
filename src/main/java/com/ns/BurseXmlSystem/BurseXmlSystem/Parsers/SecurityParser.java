package com.ns.BurseXmlSystem.BurseXmlSystem.Parsers;

import com.ns.BurseXmlSystem.BurseXmlSystem.Exceptions.WrongXmlFileException;
import com.ns.BurseXmlSystem.BurseXmlSystem.Models.Security;
import com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityParser extends Parser {

    @Autowired
    public SecurityRepository securityRepository;

    public SecurityParser() {
        super();
    }

    public void parse() throws WrongXmlFileException {

        NodeList elements = getElements("securities", "row");

        for (int i = 0; i < elements.getLength(); i++) {
            Node row = elements.item(i);

            NamedNodeMap attributes = row.getAttributes();

            if(attributes.getLength() < 16) {
                continue;
            };

            Security security = parseElement(attributes);

            List<Security> possibleSecurity = securityRepository.findBySecid(security.getSecid());
            if(possibleSecurity.size() != 0) {
                System.out.println("Security with such secid is already exists");
                continue;
            }

            securityRepository.save(security);
        }
    }

    public void parseWithSecid(String secid) throws WrongXmlFileException {
        NodeList elements = getElements("securities", "row");

        for (int i = 0; i < elements.getLength(); i++) {
            Node row = elements.item(i);

            NamedNodeMap attributes = row.getAttributes();

            if(attributes.getLength() < 16) {
                continue;
            };

            Security security;
            if(secid.compareTo(attributes.getNamedItem("secid").getNodeValue()) == 0) {
                security = parseElement(attributes);
                securityRepository.save(security);
                break;
            }
        }

    }

    public Security parseElement(NamedNodeMap attributes) {
        Security security = new Security();

        Long id = solveLongValue(attributes.getNamedItem("id").getNodeValue());
        security.setId(id);

        security.setSecid(attributes.getNamedItem("secid").getNodeValue());

        security.setShortName(attributes.getNamedItem("shortname").getNodeValue());

        security.setRegNumber(attributes.getNamedItem("regnumber").getNodeValue());

        security.setName(attributes.getNamedItem("name").getNodeName());

        security.setIsIn(attributes.getNamedItem("isin").getNodeValue());

        Boolean isTraded = solveBooleanValue(attributes.getNamedItem("is_traded").getNodeValue());
        security.setTraded(isTraded);

        Integer emitentId = solveIntegerValue(attributes.getNamedItem("emitent_id").getNodeValue());
        security.setEmitentId(emitentId);

        security.setEmitentTitle(attributes.getNamedItem("emitent_title").getNodeValue());

        security.setEmitentInn(attributes.getNamedItem("emitent_inn").getNodeValue());

        security.setEmitentOkpo(attributes.getNamedItem("emitent_okpo").getNodeValue());

        security.setGosReg(attributes.getNamedItem("gosreg").getNodeValue());

        security.setType(attributes.getNamedItem("type").getNodeValue());

        security.setGroup_(attributes.getNamedItem("group").getNodeValue());

        security.setPrimaryBoarDid(attributes.getNamedItem("primary_boardid").getNodeValue());

        security.setMarketPriceBoarDid(attributes.getNamedItem("marketprice_boardid").getNodeValue());

        return security;
    }
}
