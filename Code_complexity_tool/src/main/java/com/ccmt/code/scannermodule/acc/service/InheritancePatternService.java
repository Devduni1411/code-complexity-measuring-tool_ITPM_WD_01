package com.ccmt.code.scannermodule.acc.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InheritancePatternService{

    public String cleanStringLiterals(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile("\"(.*?)\"");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        while (textInsideDoubleQuotedMatcher.find()) {
            codeLine = codeLine.replace(textInsideDoubleQuotedMatcher.group(0), "");
        }
        return codeLine;
    }

    public int getNumberOfInheritancesJava(String cleanedCodeLine) {
        Pattern extendPattern = Pattern.compile("(\\s+)extends(\\s+|\n)");
        Matcher extendPatternMatcher = extendPattern.matcher(cleanedCodeLine);
        if (extendPatternMatcher.find()) return 1;
        return 0;
    }

    public int getNumberOfInheritancesCPP(String cleanedCodeLine) {
        int numberOfInheritances = 0;
        Pattern extendPattern = Pattern.compile(":(.*?)(\\{|\n)");
        Matcher extendPatternMatcher = extendPattern.matcher(cleanedCodeLine);

        if (extendPatternMatcher.find()){
            String extendedClassesString = extendPatternMatcher.group(0);
            String trimmedExtendedClassesString = extendedClassesString.trim();
            String[] splitExtendedClassesStrings = trimmedExtendedClassesString.split(",");

            Pattern inheritPattern = Pattern.compile("(public|private|protected)(\\s+)(.*?)");

            for (String splitExtendedClassesString : splitExtendedClassesStrings) {
                Matcher inheritPatternMatcher = inheritPattern.matcher(splitExtendedClassesString);
                if (inheritPatternMatcher.find()) numberOfInheritances++;
            }
        }

        return numberOfInheritances;
    }

    public boolean isAClass(String cleanedCodeLine) {
        Pattern pattern = Pattern.compile("(\\s+|\\}|\\{|;|\\(|^)class(\\s+|\n)");
        Matcher patternMatcher = pattern.matcher(cleanedCodeLine);
        return patternMatcher.find();
    }
}
