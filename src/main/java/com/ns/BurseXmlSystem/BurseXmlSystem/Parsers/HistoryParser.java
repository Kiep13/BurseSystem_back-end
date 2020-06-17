package com.ns.BurseXmlSystem.BurseXmlSystem.Parsers;

import com.ns.BurseXmlSystem.BurseXmlSystem.Exceptions.NoSuchSecidException;
import com.ns.BurseXmlSystem.BurseXmlSystem.Exceptions.WrongXmlFileException;
import com.ns.BurseXmlSystem.BurseXmlSystem.FileHelper;
import com.ns.BurseXmlSystem.BurseXmlSystem.Models.History;
import com.ns.BurseXmlSystem.BurseXmlSystem.Models.Security;
import com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories.HistoryRepository;
import com.ns.BurseXmlSystem.BurseXmlSystem.Repsoitories.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class HistoryParser extends Parser {

    @Autowired
    public HistoryRepository historyRepository;

    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private SecurityParser securityParser;

    private String secid = "";

    public HistoryParser() {
        super();
    }

    public void parse() throws Exception {
        NodeList elements = getElements("history", "row");

        for (int i = 0; i < elements.getLength(); i++) {
            Node row = elements.item(i);

            NamedNodeMap attributes = row.getAttributes();

            if(attributes.getLength() < 20) {
                continue;
            };

            History history = parseElement(attributes);
            this.secid = history.getSecid();

            history = connectWithSecurity(history);

            if(history.getSecurity() == null) {

                System.out.println(i + " Some errors when trying to find suitable security");
                continue;
            }

            historyRepository.save(history);
        }

    }

    public History parseElement(NamedNodeMap attributes) {

        History history = new History();
        history.setBoardid(attributes.getNamedItem("BOARDID").getNodeValue());

        Date tradeDate = null;
        try {
            tradeDate = new SimpleDateFormat("yyyy-MM-dd").parse(attributes.getNamedItem("TRADEDATE").getNodeValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        history.setTradedate(tradeDate);

        history.setShortName(attributes.getNamedItem("SHORTNAME").getNodeValue());

        history.setSecid(attributes.getNamedItem("SECID").getNodeValue());

        Double numTrades = solveDoubleValue(attributes.getNamedItem("NUMTRADES").getNodeValue());
        history.setNumTrades(numTrades);

        Double value = solveDoubleValue(attributes.getNamedItem("VALUE").getNodeValue());
        history.setValue(value);//1

        Double open = solveDoubleValue(attributes.getNamedItem("OPEN").getNodeValue());
        history.setOpen(open);

        Double low = solveDoubleValue(attributes.getNamedItem("LOW").getNodeValue());
        history.setLow(low);

        Double high = solveDoubleValue(attributes.getNamedItem("HIGH").getNodeValue());
        history.setHigh(high);

        Double legalClosePrice = solveDoubleValue(attributes.getNamedItem("LEGALCLOSEPRICE").getNodeValue());
        history.setLegalClosePrice(legalClosePrice);

        Double waprice = solveDoubleValue(attributes.getNamedItem("WAPRICE").getNodeValue());
        history.setWaprice(waprice);

        Double close = solveDoubleValue(attributes.getNamedItem("CLOSE").getNodeValue());
        history.setClose(close);

        Double volume = solveDoubleValue(attributes.getNamedItem("VOLUME").getNodeValue());
        history.setVolume(volume);

        Double marketPrice2 = solveDoubleValue(attributes.getNamedItem("MARKETPRICE2").getNodeValue());
        history.setMarketPrice2(marketPrice2);

        Double marketPrice3 = solveDoubleValue(attributes.getNamedItem("MARKETPRICE3").getNodeValue());
        history.setMarketPrice3(marketPrice3);

        Double admittedQuite = solveDoubleValue(attributes.getNamedItem("ADMITTEDQUOTE").getNodeValue());
        history.setAdmittedQuite(admittedQuite);

        Double mp2ValTrd = solveDoubleValue(attributes.getNamedItem("MP2VALTRD").getNodeValue());
        history.setMp2ValTrd(mp2ValTrd);

        Double marketPrice3TradeValue = solveDoubleValue(
                attributes.getNamedItem("MARKETPRICE3TRADESVALUE").getNodeValue());
        history.setMarketPrice3TradeValue(marketPrice3TradeValue);

        Double admittedValue = solveDoubleValue(attributes.getNamedItem("ADMITTEDVALUE").getNodeValue());
        history.setAdmittedValue(admittedValue);

        Double waVal = solveDoubleValue(attributes.getNamedItem("WAVAL").getNodeValue());
        history.setWaVal(waVal);

        return history;
    }

    public ArrayList<Security> findSuitableSecurity() {
        System.out.println(securityRepository.findBySecid(this.secid));
        return securityRepository.findBySecid(this.secid);
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public History connectWithSecurity(History history) throws Exception {
        try {
            List<Security> securitiesList = findSuitableSecurity();

            if (securitiesList.size() != 1) {

                getSecurity();
                securitiesList = findSuitableSecurity();

                if (securitiesList.size() != 1) {
                    throw new NoSuchSecidException();
                }
            }
            history.setSecurity(securitiesList.get(0));

        } catch(NoSuchSecidException e) {
            System.out.println("Catch some problems with identifying secid");
        }
        return history;
    }

    public void getSecurity() {

        HttpURLConnection connection = createConnection();
        StringBuilder stringBuilder = new StringBuilder();

        try {

            connection.connect();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while((line = in.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }

                FileHelper fileHelper = new FileHelper();
                File responseFile = fileHelper.createFile(this.secid, stringBuilder);

                securityParser.setFile(responseFile);
                securityParser.parseWithSecid(this.secid);
                responseFile.delete();

            }
        } catch (IOException | WrongXmlFileException e) {
            e.printStackTrace();
        }
    }

    public HttpURLConnection createConnection() {
        String url = "http://iss.moex.com/iss/securities.xml?q=" + this.secid;
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert connection != null;
        connection.setUseCaches(false);
        return connection;
    }
}
