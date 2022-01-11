package com.softserve.entity;

public class Badge {
    protected int id;
    protected int codeF;
    protected String nameF;
    protected double sumE;
    protected double gravity;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getCodeF() {
	return codeF;
    }

    public void setCodeF(int codeF) {
	this.codeF = codeF;
    }

    public String getNameF() {
	return nameF;
    }

    public void setNameF(String nameF) {
	this.nameF = nameF;
    }

    public double getSumE() {
	return sumE;
    }

    public void setSumE(double sumE) {
	this.sumE = sumE;
    }

    public double getGravity() {
	return gravity;
    }

    public void setGravity(double gravity) {
	this.gravity = gravity;
    }

    public Badge() {
    }

    public Badge(int codeF, String nameF, double sumE, double gravity) {
	this.codeF = codeF;
	this.nameF = nameF;
	this.sumE = sumE;
	this.gravity = gravity;
    }

    @Override
    public String toString() {
	return "ID" + id + ", Functional classification code: " + codeF
		+ ", Name of the functional classification code: " + nameF + ", Sum of values: " + sumE
		+ ", Specific gravity: " + gravity;
    }

}