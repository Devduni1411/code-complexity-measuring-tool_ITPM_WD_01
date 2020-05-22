package com.ccmt.code.scannermodule.acc.service.cpp;

import com.sliit.cde.scannermodule.acc.service.MethodAndVariableStringPatternService;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPPLanguageMethodAndVariableStringPatternService extends MethodAndVariableStringPatternService {

    public List<String> getPrimitiveDataTypes() {
        String operatorsString = "int,float,double,bool,char";
        return Arrays.asList(operatorsString.split(","));
    }

    public String getReturnVariableTypeOfMethod(String codeLine) {
        if (isMethod(codeLine)) {
            codeLine = this.removeWord(codeLine, "static");
            String newCodeLine = this.removeWord(codeLine, "private|protected|public");

            String delimiter = "\\s+";
            Pattern pat = Pattern.compile(delimiter, Pattern.CASE_INSENSITIVE);
            String[] scattered = pat.split(newCodeLine);

            for (String s: scattered) {
                if (!s.equals("")) return s;
            }
        }
        return null;
    }

    public boolean isPrimitiveVariable(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile(
                "(\\s+|^)(int|float|double|bool|char)(\\s+)(.*?);");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        boolean r = textInsideDoubleQuotedMatcher.find();
        return r;
    }

    public boolean isPrimitiveParameter(String parameter) {
        Pattern squareBracket = Pattern.compile("\\[(.*?)\\]");
        Matcher squareBracketMatcher = squareBracket.matcher(parameter);
        boolean isArray = squareBracketMatcher.find();

        boolean isPrimitive = isSubString(parameter, "int|float|double|boolean|char");

        return !isArray && isPrimitive;
    }
}
