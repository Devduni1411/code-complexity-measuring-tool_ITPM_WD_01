package com.ccmt.code.scannermodule.acc.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlStructurePatternService {

    public String cleanStringLiterals(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile("\"(.*?)\"");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        while (textInsideDoubleQuotedMatcher.find()) {
            codeLine = codeLine.replace(textInsideDoubleQuotedMatcher.group(0), "");
        }
        return codeLine;
    }

    public int getNumberOfIfStatements(String cleanedCodeLine) {
        int numberOfIfStatements = 0;
        Pattern pattern = Pattern.compile("(\\s+|\\{|\\}|;|\\(|^)if(\\s*)(\\()");
        Matcher patternMatcher = pattern.matcher(cleanedCodeLine);
        while (patternMatcher.find()) {
            numberOfIfStatements++;
        }
        return numberOfIfStatements;
    }

    public int getNumberOfLoops(String cleanedCodeLine) {
        int numberOfLoops = 0;
        Pattern pattern = Pattern.compile("((\\s+|\\{|\\}|;|\\(|^)(for|while)(\\s*)(\\())|" +
                "((\\s+|\\{|\\}|;|\\(|^)(do\\s*\\{))");
        Matcher patternMatcher = pattern.matcher(cleanedCodeLine);
        while (patternMatcher.find()) {
            numberOfLoops++;
        }
        return numberOfLoops;
    }

    public int getNumberOfSwitchStatements(String cleanedCodeLine) {
        int numberOfSwitchesStatements = 0;
        Pattern pattern = Pattern.compile("(\\s+|\\{|\\}|;|\\(|^)switch(\\s*)(\\()");
        Matcher patternMatcher = pattern.matcher(cleanedCodeLine);
        while (patternMatcher.find()) {
            numberOfSwitchesStatements++;
        }
        return numberOfSwitchesStatements;
    }

    public int getNumberOfCaseStatements(String cleanedCodeLine) {
        int numberOfCaseStatements = 0;
        Pattern pattern = Pattern.compile("(\\s+|\\{|;|^)case(\\s+)");
        Matcher patternMatcher = pattern.matcher(cleanedCodeLine);
        while (patternMatcher.find()) {
            numberOfCaseStatements++;
        }
        return numberOfCaseStatements;
    }
}
