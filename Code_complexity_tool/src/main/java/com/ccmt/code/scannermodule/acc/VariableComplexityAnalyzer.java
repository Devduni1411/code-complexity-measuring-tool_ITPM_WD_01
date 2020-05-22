package com.ccmt.code.scannermodule.acc;

import com.ccmt.code.coremodule.model.Line;
import com.ccmt.code.coremodule.model.VariableComplexity;
import com.ccmt.code.scannermodule.acc.enums.Language;
import com.ccmt.code.scannermodule.acc.enums.Scope;
import com.ccmt.code.scannermodule.acc.service.cpp.CPPLanguageMethodAndVariableStringPatternService;
import com.ccmt.code.scannermodule.acc.service.java.JavaLanguageMethodAndVariableStringPatternService;
import com.ccmt.code.scannermodule.acc.service.MethodAndVariableStringPatternService;

class VariableComplexityAnalyzer {
    private Scope scope = Scope.GLOBAL;
    private MethodAndVariableStringPatternService methodAndVariableStringPatternService
            = new JavaLanguageMethodAndVariableStringPatternService();
    private int scopeLevel = 0;

    public VariableComplexityAnalyzer(Language language) {
        if (language == Language.JAVA)
            methodAndVariableStringPatternService = new JavaLanguageMethodAndVariableStringPatternService();
        else if (language == Language.CPP)
            methodAndVariableStringPatternService = new CPPLanguageMethodAndVariableStringPatternService();
    }

    void analyze(Line lineObject, String codeLine) {
        VariableComplexity variableComplexity = new VariableComplexity();
        String cleanedCodeLine = methodAndVariableStringPatternService.cleanStringLiterals(codeLine);

        scopeLevel += methodAndVariableStringPatternService.getNumberOpenCurlBrackets(cleanedCodeLine);
        scopeLevel -= methodAndVariableStringPatternService.getNumberCloseCurlBrackets(cleanedCodeLine);

        if (scopeLevel == 1) {
            variableComplexity.setScopeGlobal(1);
            variableComplexity.setScopeLocal(0);
        } else if (scopeLevel > 1) {
            variableComplexity.setScopeGlobal(0);
            variableComplexity.setScopeLocal(1);
        }

        if (methodAndVariableStringPatternService.isVariable(cleanedCodeLine) &&
                methodAndVariableStringPatternService.isPrimitiveVariable(cleanedCodeLine)) {
            variableComplexity.setNumberOfPrimitiveDataTypeVariables(1);
        } else if (methodAndVariableStringPatternService.isVariable(cleanedCodeLine) &&
                methodAndVariableStringPatternService.isCompositeVariable(cleanedCodeLine)) {
            variableComplexity.setNumberOfCompositeDataTypeVariables(1);
        }

        lineObject.setVariableComplexity(variableComplexity);
    }
}
