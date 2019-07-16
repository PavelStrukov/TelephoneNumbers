/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telephonenumbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Main {

    ArrayList<BigInteger> telephoneNumbers;
    ArrayList<Template> listTemplates;
    ArrayList<Template> resultNumbers;

    private void start() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int n = Integer.parseInt(line);
        telephoneNumbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            line = sc.nextLine();
            telephoneNumbers.add(parseNumber(line));
        }

        line = sc.nextLine();
        int m = Integer.parseInt(line);
        listTemplates = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            line = sc.nextLine();
            listTemplates.add(parseTemplate(line));
        }
    }

    private BigInteger parseNumber(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        String numberString = sb.toString();
        return new BigInteger(numberString);
    }

    private Template parseTemplate(String line) {
        int countryCode = 0;
        int operatorCode = 0;
        String personalNumber = null;
        String country = null;
        String operator = null;

        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (i < line.length()) {
            while (line.charAt(i) != ' ') {
                sb.append(line.charAt(i));
                i++;
            }
            String tempStr = sb.toString();
            countryCode = Integer.parseInt(tempStr);
            i = i + 2;
            sb.setLength(0);
            while (line.charAt(i) != ')') {
                sb.append(line.charAt(i));
                i++;
            }
            tempStr = sb.toString();
            operatorCode = Integer.parseInt(tempStr);
            i = i + 2;
            sb.setLength(0);
            while (line.charAt(i) != ' ') {
                sb.append(line.charAt(i));
                i++;
            }
            personalNumber = sb.toString();
            i = i + 3;
            sb.setLength(0);
            while (line.charAt(i) != ' ') {
                sb.append(line.charAt(i));
                i++;
            }
            country = sb.toString();
            i++;
            sb.setLength(0);

            while (i != line.length()) {
                sb.append(line.charAt(i));
                i++;
            }
            operator = sb.toString();
        }

        return new Template(countryCode, operatorCode, personalNumber,
                country, operator);
    }

    private void defineEachNumber() {
        resultNumbers = new ArrayList<>();
        for (BigInteger telephoneNumber : telephoneNumbers) {
            String telephoneNumberString = telephoneNumber.toString();
            for (Template template : listTemplates) {
                if (isNumberSatisfyTemplate(telephoneNumberString, template)) {
                    resultNumbers.add(createDefinedNumberWithTemplate(
                            telephoneNumberString, template));
                    break;
                }
            }
        }
    }

    private boolean isNumberSatisfyTemplate(String number, Template template) {
        String templateNumber = template.getNumber();
        int firstX = templateNumber.indexOf('X');
        String templateNumberJustDigits = templateNumber.substring(0, firstX);
        String numberFirstPart = number.substring(0, firstX);
        int countX = templateNumber.length() - firstX;
        int numberSecondPartLength = number.length() - numberFirstPart.length();
        return ((templateNumberJustDigits.equals(numberFirstPart)) && (countX == numberSecondPartLength));
    }

    private Template createDefinedNumberWithTemplate(String number, Template template) {
        String templateString = template.toString();
        Template definedNumber = parseTemplate(templateString);
        definedNumber.setPersonalNumber(number);
        return definedNumber;
    }

    private void printResult() {
        System.out.println("---------------------- R E S U L T ---------------------");
        for (Template definedNumber : resultNumbers) {
            definedNumber.print();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();

        main.defineEachNumber();

        main.printResult();
    }

}
