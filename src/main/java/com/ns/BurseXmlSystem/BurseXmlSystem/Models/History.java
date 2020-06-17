package com.ns.BurseXmlSystem.BurseXmlSystem.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "histories")
public class History {

    @Id
    @GeneratedValue
    private Long id;
    private String boardid;
    private Date tradedate;
    private String shortName;
    private String secid;
    private Double numTrades;
    private Double value;
    private Double open;
    private Double low;
    private Double high;
    private Double legalClosePrice;
    private Double waprice;
    private Double close;
    private Double volume;
    private Double marketPrice2;
    private Double marketPrice3;
    private  Double admittedQuite;
    private Double mp2ValTrd;
    private Double marketPrice3TradeValue;
    private Double admittedValue;
    private Double waVal;

    @ManyToOne(optional=false/*, cascade=CascadeType.ALL*/)
    @JoinColumn (name="security_id")
    private Security security;

    public History() {
    }

    public History(String boardid, Date tradedate, String shortName, String secid, Double numTrades, Double value,
                   Double open, Double low, Double high, Double legalClosePrice, Double waprice, Double close,
                   Double volume, Double marketPrice2, Double marketPrice3, Double admittedQuite, Double mp2ValTrd,
                   Double marketPrice3TradeValue, Double admittedValue, Double waVal, Security security) {
        this.boardid = boardid;
        this.tradedate = tradedate;
        this.shortName = shortName;
        this.secid = secid;
        this.numTrades = numTrades;
        this.value = value;
        this.open = open;
        this.low = low;
        this.high = high;
        this.legalClosePrice = legalClosePrice;
        this.waprice = waprice;
        this.close = close;
        this.volume = volume;
        this.marketPrice2 = marketPrice2;
        this.marketPrice3 = marketPrice3;
        this.admittedQuite = admittedQuite;
        this.mp2ValTrd = mp2ValTrd;
        this.marketPrice3TradeValue = marketPrice3TradeValue;
        this.admittedValue = admittedValue;
        this.waVal = waVal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public Date getTradedate() {
        return tradedate;
    }

    public void setTradedate(Date tradedate) {
        this.tradedate = tradedate;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public Double getNumTrades() {
        return numTrades;
    }

    public void setNumTrades(Double numTrades) {
        this.numTrades = numTrades;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLegalClosePrice() {
        return legalClosePrice;
    }

    public void setLegalClosePrice(Double legalClosePrice) {
        this.legalClosePrice = legalClosePrice;
    }

    public Double getWaprice() {
        return waprice;
    }

    public void setWaprice(Double waprice) {
        this.waprice = waprice;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getMarketPrice2() {
        return marketPrice2;
    }

    public void setMarketPrice2(Double marketPrice2) {
        this.marketPrice2 = marketPrice2;
    }

    public Double getMarketPrice3() {
        return marketPrice3;
    }

    public void setMarketPrice3(Double marketPrice3) {
        this.marketPrice3 = marketPrice3;
    }

    public Double getAdmittedQuite() {
        return admittedQuite;
    }

    public void setAdmittedQuite(Double admittedQuite) {
        this.admittedQuite = admittedQuite;
    }

    public Double getMp2ValTrd() {
        return mp2ValTrd;
    }

    public void setMp2ValTrd(Double mp2ValTrd) {
        this.mp2ValTrd = mp2ValTrd;
    }

    public Double getMarketPrice3TradeValue() {
        return marketPrice3TradeValue;
    }

    public void setMarketPrice3TradeValue(Double marketPrice3TradeValue) {
        this.marketPrice3TradeValue = marketPrice3TradeValue;
    }

    public Double getAdmittedValue() {
        return admittedValue;
    }

    public void setAdmittedValue(Double admittedValue) {
        this.admittedValue = admittedValue;
    }

    public Double getWaVal() {
        return waVal;
    }

    public void setWaVal(Double waVal) {
        this.waVal = waVal;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    @Override
    public String
    toString() {
        return "History{" +
                "boardid='" + boardid + '\'' +
                ", tradedate=" + tradedate +
                ", shortName='" + shortName + '\'' +
                ", secid='" + secid + '\'' +
                ", numTrades=" + numTrades +
                ", value=" + value +
                ", open=" + open +
                ", low=" + low +
                ", high=" + high +
                ", legalClosePrice=" + legalClosePrice +
                ", waprice=" + waprice +
                ", close=" + close +
                ", volume=" + volume +
                ", marketPrice2=" + marketPrice2 +
                ", marketPrice3=" + marketPrice3 +
                ", admittedQuite=" + admittedQuite +
                ", mp2ValTrd=" + mp2ValTrd +
                ", marketPrice3TradeValue=" + marketPrice3TradeValue +
                ", admittedValue=" + admittedValue +
                ", waVal=" + waVal +
                '}';
    }
}
