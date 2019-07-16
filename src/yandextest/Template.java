/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yandextest;

/**
 *
 * @author Dell
 */
public class Template extends Number {

    private String country;
    private String operator;

    public Template(int countryCode, int operatorCode, String persNumber,
            String country, String operator) {
        super(countryCode, operatorCode, persNumber);
        this.country = country;
        this.operator = operator;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" - ");
        sb.append(this.country);
        sb.append(" ");
        sb.append(this.operator);
        return sb.toString();
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

}
