package com.softserve.entity;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

public class NationalBudget {
    protected int id;
    protected int codeB;
    protected int codeF;
    protected String nameExpend;
    protected double expendG;
    protected double consumG;
    protected double develG;
    protected double expendS;
    protected double consumS;
    protected double develS;
    protected double expenditures;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodeB() {
        return codeB;
    }

    public void setCodeB(int codeB) {
        this.codeB = codeB;
    }

    public int getCodeF() {
        return codeF;
    }

    public void setCodeF(int codeF) {
        this.codeF = codeF;
    }

    public String getNameExpend() {
        return nameExpend;
    }

    public void setNameExpend(String nameExpend) {
        this.nameExpend = nameExpend;
    }

    public double getExpendG() {
        return expendG;
    }

    public void setExpendG(double expendG) {
        this.expendG = expendG;
    }

    public double getConsumG() {
        return consumG;
    }

    public void setConsumG(double consumG) {
        this.consumG = consumG;
    }

    public double getDevelG() {
        return develG;
    }

    public void setDevelG(double develG) {
        this.develG = develG;
    }

    public double getExpendS() {
        return expendS;
    }

    public void setExpendS(double expendS) {
        this.expendS = expendS;
    }

    public double getConsumS() {
        return consumS;
    }

    public void setConsumS(double consumS) {
        this.consumS = consumS;
    }

    public double getDevelS() {
        return develS;
    }

    public void setDevelS(double develS) {
        this.develS = develS;
    }

    public double getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(double expenditures) {
        this.expenditures = expenditures;
    }

    public NationalBudget(int codeB, int codeF, String nameExpend, double expendG, double consumG, double develG,
                          double expendS, double consumS, double develS, double expenditures) {
        this.codeB = codeB;
        this.codeF = codeF;
        this.nameExpend = nameExpend;
        this.expendG = expendG;
        this.consumG = consumG;
        this.develG = develG;
        this.expendS = expendS;
        this.consumS = consumS;
        this.develS = develS;
        this.expenditures = expenditures;
    }

    public NationalBudget() {
    }

    @Override
    public String toString() {
        return "ID" + id + ", Budget classification code: " + codeB + ", Functional classification code: " + codeF
                + ", Name item of expenditure: " + nameExpend + ", General fund: " + expendG
                + ", Consumption costs GF: " + consumG + ", Development costs GF: " + develG + ", Special fund: "
                + expendS + ", Consumption costs SF: " + consumS + ", Development costs SF: " + develS
                + ", State budget expenditures: " + expenditures;
    }

}
