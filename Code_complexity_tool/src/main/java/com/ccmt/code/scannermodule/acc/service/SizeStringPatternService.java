package com.ccmt.code.scannermodule.acc.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SizeStringPatternService {
    abstract protected String getOrSeparatedKeywords();
    abstract protected String getOrSeparatedIdentifierPrefixes();

    public String cleanStringLiterals(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile("\"(.*?)\"");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        while (textInsideDoubleQuotedMatcher.find()) {
            codeLine = codeLine.replace(textInsideDoubleQuotedMatcher.group(0), "");
        }
        return codeLine;
    }

    public int getNumberOfKeyWords(String codeLine) {
        int numberOfKeyWords = 0;
        Pattern keyWordsPattern = Pattern.compile(this.getOrSeparatedKeywords());
        Matcher keyWordsPatternMatcher = keyWordsPattern.matcher(codeLine);
        while (keyWordsPatternMatcher.find()) {
            numberOfKeyWords++;
        }
        return numberOfKeyWords;
    }

    public int getNumberOfIdentifiers(String codeLine) {
        int numberOfIdentifiers = 0;
        Pattern identifierPrefixesPattern = Pattern.compile(this.getOrSeparatedIdentifierPrefixes());
        Matcher identifierPatternMatcher = identifierPrefixesPattern.matcher(codeLine);
        while (identifierPatternMatcher.find()) {
            numberOfIdentifiers++;
        }
        return numberOfIdentifiers;
    }

    public int getNumberOfOperators(String codeLine) {
        int numberOfOperators = 0;
        Pattern operatorsPattern = Pattern.compile("\\+\\+|\\-\\-|\\=\\=|\\!\\=|\\>|\\<|\\>\\=|\\<\\=|" +
                "\\&\\&|\\|\\||!|\\||\\^|\\~|\\<\\<|\\>\\>|\\>\\>\\>|\\<\\<\\<|\\-\\>|\\'|\\.|\\:\\:|\\+\\=|\\-\\=|" +
                "\\*\\=|\\/\\=|\\=|\\>\\>\\>\\=|\\|\\=|\\&\\=|\\%\\=|\\<\\<\\=|\\>\\>\\=|\\^\\=|\\+|-|\\*|\\/|\\%");
        Matcher operatorPatternMatcher = operatorsPattern.matcher(codeLine);
        while (operatorPatternMatcher.find()) {
            numberOfOperators++;
        }
        return numberOfOperators;
    }

    public int getNumberOfNumericValues(String codeLine) {
        int numberOfNumericValues = 0;
        Pattern numericValuePattern = Pattern.compile("\\d+[.]\\d+|\\d+");
        Matcher numericValuePatternMatcher = numericValuePattern.matcher(codeLine);
        while (numericValuePatternMatcher.find()) {
            numberOfNumericValues++;
        }
        return numberOfNumericValues;
    }

    public int getNumberOfStringLiterals(String codeLine) {
        int numberOfStringLiterals = 0;
        Pattern stringLiteralsPattern = Pattern.compile("\"(.*?)\"");
        Matcher stringLiteralsPatternMatcher = stringLiteralsPattern.matcher(codeLine);
        while (stringLiteralsPatternMatcher.find()) {
            numberOfStringLiterals++;
        }
        return numberOfStringLiterals;
    }
}
