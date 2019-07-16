/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telephonenumbers;

/**
 *
 * @author Dell
 */
public class Number {

    private int countryCode;
    private int operatorCode;
    private String personalNumber;

    public Number(int countryCode, int operatorCode, String persNumber) {
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.personalNumber = persNumber;
    }

    public String getNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.countryCode);
        sb.append(this.operatorCode);
        sb.append(this.personalNumber);
        return sb.toString();
    }

    public void setPersonalNumber(String number) {
        int lengthPersonalNumber = this.personalNumber.length();
        String partNumber = number.substring(number.length() - lengthPersonalNumber);
        this.personalNumber = partNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        sb.append(this.countryCode);
        sb.append(" (");
        sb.append(this.operatorCode);
        sb.append(") ");
        sb.append(this.personalNumber);
        return sb.toString();
    }

    public void print() {
        System.out.println(this.toString());
    }
}
