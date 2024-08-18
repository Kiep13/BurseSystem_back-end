package com.ns.BurseXmlSystem.BurseXmlSystem.Models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "securities")
public class Security {

    @Id
    @GeneratedValue
    private Long id;
    private String secid;
    private String shortName = "";
    private String regNumber;
    private String name;
    private String isIn;
    private Boolean isTraded;
    private Integer emitentId;
    private String emitentTitle;
    private String emitentInn;
    private String emitentOkpo;
    private String gosReg;
    private String type;
    private String group_;
    private String primaryBoarDid;
    private String marketPriceBoarDid;

    @OneToMany(mappedBy="security")
    private List<History> histories;

    public Security() {

    }

    public Security(Long id, String secid, String shortName, String regNumber, String name, String isIn,
                    Boolean isTraded, Integer emitentId, String emitentTitle, String emitentInn, String emitentOkpo,
                    String gosReg, String type, String group, String primaryBoarDid, String marketPriceBoarDid) {
        this.id = id;
        this.secid = secid;
        this.shortName = shortName;
        this.regNumber = regNumber;
        this.name = name;
        this.isIn = isIn;
        this.isTraded = isTraded;
        this.emitentId = emitentId;
        this.emitentTitle = emitentTitle;
        this.emitentInn = emitentInn;
        this.emitentOkpo = emitentOkpo;
        this.gosReg = gosReg;
        this.type = type;
        this.group_ = group;
        this.primaryBoarDid = primaryBoarDid;
        this.marketPriceBoarDid = marketPriceBoarDid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsIn() {
        return isIn;
    }

    public void setIsIn(String isIn) {
        this.isIn = isIn;
    }

    public Boolean getTraded() {
        return isTraded;
    }

    public void setTraded(Boolean traded) {
        isTraded = traded;
    }

    public Integer getEmitentId() {
        return emitentId;
    }

    public void setEmitentId(Integer emitentId) {
        this.emitentId = emitentId;
    }

    public String getEmitentTitle() {
        return emitentTitle;
    }

    public void setEmitentTitle(String emitentTitle) {
        this.emitentTitle = emitentTitle;
    }

    public String getEmitentInn() {
        return emitentInn;
    }

    public void setEmitentInn(String emitentInn) {
        this.emitentInn = emitentInn;
    }

    public String getEmitentOkpo() {
        return emitentOkpo;
    }

    public void setEmitentOkpo(String emitentOkpo) {
        this.emitentOkpo = emitentOkpo;
    }

    public String getGosReg() {
        return gosReg;
    }

    public void setGosReg(String gosReg) {
        this.gosReg = gosReg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup_() {
        return group_;
    }

    public void setGroup_(String group) {
        this.group_ = group;
    }

    public String getPrimaryBoarDid() {
        return primaryBoarDid;
    }

    public void setPrimaryBoarDid(String primaryBoarDid) {
        this.primaryBoarDid = primaryBoarDid;
    }

    public String getMarketPriceBoarDid() {
        return marketPriceBoarDid;
    }

    public void setMarketPriceBoarDid(String marketPriceBoarDid) {
        this.marketPriceBoarDid = marketPriceBoarDid;
    }

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", secid='" + secid + '\'' +
                ", shortName='" + shortName + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", name='" + name + '\'' +
                ", isIn='" + isIn + '\'' +
                ", isTraded=" + isTraded +
                ", emitentId=" + emitentId +
                ", emitentTitle='" + emitentTitle + '\'' +
                ", emitentInn='" + emitentInn + '\'' +
                ", emitentOkpo='" + emitentOkpo + '\'' +
                ", gosReg='" + gosReg + '\'' +
                ", type='" + type + '\'' +
                ", group='" + group_ + '\'' +
                ", primaryBoarDid='" + primaryBoarDid + '\'' +
                ", marketPriceBoarDid='" + marketPriceBoarDid + '\'' +
                '}';
    }
}
