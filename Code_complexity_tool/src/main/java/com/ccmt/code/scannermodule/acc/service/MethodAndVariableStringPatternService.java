package com.ccmt.code.scannermodule.acc.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MethodAndVariableStringPatternService {

    abstract protected List<String> getPrimitiveDataTypes();
    public abstract String getReturnVariableTypeOfMethod(String codeLine);
    abstract public boolean isPrimitiveVariable(String codeLine);
    abstract public boolean isPrimitiveParameter(String parameter);

    public String cleanStringLiterals(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile("\"(.*?)\"");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        while (textInsideDoubleQuotedMatcher.find()) {
            codeLine = codeLine.replace(textInsideDoubleQuotedMatcher.group(0), "");
        }
        return codeLine;
    }

    protected String removeWord(String target, String word) {
        Pattern wordPattern = Pattern.compile(word);
        Matcher wordMatcher = wordPattern.matcher(target);
        while (wordMatcher.find())
            target = target.replace(wordMatcher.group(0), "");
        return target;
    }

    protected boolean isSubString(String target, String word) {
        Pattern wordPattern = Pattern.compile(word);
        Matcher wordMatcher = wordPattern.matcher(target);
        return wordMatcher.find();
    }

    protected int getNumberOfSubstrings(String target, String word) {
        int number = 0;
        Pattern wordPattern = Pattern.compile(word);
        Matcher wordMatcher = wordPattern.matcher(target);
        while (wordMatcher.find()) number++;
        return number;
    }

    public boolean c(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile("public(\\s+)(static|nonstatic|^$)(\\s+)void");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        boolean r = textInsideDoubleQuotedMatcher.find();
//        if (r) System.out.println(textInsideDoubleQuotedMatcher.group(0));
        return r;
    }

    public boolean isMethod(String codeLine) {
        Pattern methodStringPattern = Pattern.compile("(\\s+|^)(.*?)(\\(.*?\\))(.*?)(\\{|\n)");
        Matcher methodStringPatternMatcher = methodStringPattern.matcher(codeLine);
        boolean r = methodStringPatternMatcher.find();

        Pattern assignment = Pattern.compile("(=)");
        Matcher assignmentMatcher = assignment.matcher(codeLine);
        boolean assignmentExist = assignmentMatcher.find();

        Pattern semiColan = Pattern.compile("(;)");
        Matcher semiColanMatcher = semiColan.matcher(codeLine);
        boolean semiColanExist = semiColanMatcher.find();

        boolean datatypeInBrackets = true;

        return r && !assignmentExist && !semiColanExist && datatypeInBrackets;
    }

    public boolean isPrimitiveDataType(String datatype) {
        List<String> primitiveDataTypes = this.getPrimitiveDataTypes();
        return primitiveDataTypes.contains(datatype);
    }

    public boolean isVoidDataType(String retType) {
        return retType.equals("void");
    }

    public boolean isCompositeVariable(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile(
                "(\\s+|^)(\\s+)(.*?);");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        boolean r = textInsideDoubleQuotedMatcher.find();
        return r;
    }

    public boolean isVariable(String codeLine) {
        boolean rejectedWordIncluded = this.isSubString(codeLine, "return");
        boolean isMethodCall = isSubString(codeLine, "[(](.*?)[)]") && !isSubString(codeLine,"=");

        if (isSubString(codeLine,"=")) {
            String[] splitCodeLineByAssignment = codeLine.split("=");
            return splitCodeLineByAssignment[0].trim().split("\\s+").length >= 2;
        }

        return !rejectedWordIncluded && !isMethodCall;
    }

    public int getNumberOpenCurlBrackets(String codeLine) {
        int numberOfStringLiterals = 0;
        Pattern textInsideDoubleQuoted = Pattern.compile("[{]");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        while (textInsideDoubleQuotedMatcher.find()) {
            numberOfStringLiterals++;
        }
        return numberOfStringLiterals;
    }

    public int getNumberCloseCurlBrackets(String codeLine) {
        int numberOfStringLiterals = 0;
        Pattern textInsideDoubleQuoted = Pattern.compile("[}]");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        while (textInsideDoubleQuotedMatcher.find()) {
            numberOfStringLiterals++;
        }
        return numberOfStringLiterals;
    }

    public String getCodeInsideBrackets(String codeLine) {
        String firstBreak =  codeLine.split("\\(", 2)[1];
        int index = firstBreak.lastIndexOf(")");

        if (index > 1)
            return firstBreak.substring(0, index);
        else return null;
    }

    public int getNumberOfPrimitiveParameters(String parametersString) {
        int numberOfPrimitiveParameters = 0;
        String[] splitParameters = parametersString.split(",");

        for (String parameter: splitParameters) {
            if (isPrimitiveParameter(parameter)) numberOfPrimitiveParameters++;
        }
        return numberOfPrimitiveParameters;
    }

    public int getNumberOfCompositeParameters(String parametersString) {
        int numberOfCompositeParameters = 0;
        String[] splitParameters = parametersString.split(",");

        for (String parameter: splitParameters) {
            if (!isPrimitiveParameter(parameter)) numberOfCompositeParameters++;
        }
        return numberOfCompositeParameters;
    }
}
