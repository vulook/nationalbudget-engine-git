package com.softserve.entity;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

public class BudgetMetodURL {

    public static String switchYear(int number) {

        switch (number) {

            case 2021:
                     return "https://zakon.rada.gov.ua/laws/file/text/94/f501426n203.xls";

            default:
                return "Nothing";
        }
    }

}
