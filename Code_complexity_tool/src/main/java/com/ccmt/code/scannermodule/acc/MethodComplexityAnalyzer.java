package com.ccmt.code.scannermodule.acc;

import com.ccmt.code.coremodule.model.Line;
import com.ccmt.code.coremodule.model.MethodComplexity;
import com.ccmt.code.scannermodule.acc.enums.Language;
import com.ccmt.code.scannermodule.acc.enums.Scope;
import com.ccmt.code.scannermodule.acc.service.cpp.CPPLanguageMethodAndVariableStringPatternService;
import com.ccmt.code.scannermodule.acc.service.java.JavaLanguageMethodAndVariableStringPatternService;
import com.ccmt.code.scannermodule.acc.service.MethodAndVariableStringPatternService;

class MethodComplexityAnalyzer {
    private Scope scope = Scope.GLOBAL;
    private MethodAndVariableStringPatternService MethodAndVariableStringPatternService
            = new JavaLanguageMethodAndVariableStringPatternService();
    private int scopeLevel = 0;

    public MethodComplexityAnalyzer(Language language) {
        if (language == Language.JAVA)
            MethodAndVariableStringPatternService = new JavaLanguageMethodAndVariableStringPatternService();
        else if (language == Language.CPP)
            MethodAndVariableStringPatternService = new CPPLanguageMethodAndVariableStringPatternService();
    }

    void analyze(Line lineObject, String codeLine) {
        MethodComplexity methodComplexity = new MethodComplexity();
        String cleanedCodeLine = MethodAndVariableStringPatternService.cleanStringLiterals(codeLine);
        System.out.println(codeLine);
        if (scopeLevel == 1 && MethodAndVariableStringPatternService.isMethod(cleanedCodeLine)) {
            String retType = MethodAndVariableStringPatternService.getReturnVariableTypeOfMethod(cleanedCodeLine);

            boolean isPrimitiveReturnType = MethodAndVariableStringPatternService.isPrimitiveDataType(retType);
            boolean isVoidReturnType = MethodAndVariableStringPatternService.isVoidDataType(retType);

            if (isPrimitiveReturnType) {
                methodComplexity.setReturnType(1);
            } else if (isVoidReturnType) {
                methodComplexity.setReturnType(0);
            } else {
                methodComplexity.setReturnType(-1);
            }

            String parametersString = MethodAndVariableStringPatternService.getCodeInsideBrackets(cleanedCodeLine);

            if (parametersString != null) {

                methodComplexity
                        .setNumberOfPrimitiveDataTypeParameters(
                                MethodAndVariableStringPatternService.getNumberOfPrimitiveParameters(parametersString));

                methodComplexity
                        .setNumberOfCompositeDataTypeParameters(
                                MethodAndVariableStringPatternService.getNumberOfCompositeParameters(parametersString));
            }
        }

        scopeLevel += MethodAndVariableStringPatternService.getNumberOpenCurlBrackets(cleanedCodeLine);
        scopeLevel -= MethodAndVariableStringPatternService.getNumberCloseCurlBrackets(cleanedCodeLine);

        lineObject.setMethodComplexity(methodComplexity);
    }
}
