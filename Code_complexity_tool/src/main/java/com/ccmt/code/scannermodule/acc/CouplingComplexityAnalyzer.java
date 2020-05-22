package com.ccmt.code.scannermodule.acc;

import com.ccmt.code.coremodule.model.CouplingComplexity;
import com.ccmt.code.coremodule.model.Line;
import com.ccmt.code.scannermodule.acc.enums.Method;
import com.ccmt.code.scannermodule.acc.service.CouplingPatternService;
import com.ccmt.code.scannermodule.acc.service.MethodAndVariableStringPatternService;
import com.ccmt.code.scannermodule.acc.service.java.JavaLanguageMethodAndVariableStringPatternService;

import java.util.List;

public class CouplingComplexityAnalyzer {
    private CouplingPatternService couplingPatternService = new CouplingPatternService();
    private MethodAndVariableStringPatternService MethodAndVariableStringPatternService
            = new JavaLanguageMethodAndVariableStringPatternService();
    private int scopeLevel = 0;

    void analyze(Line lineObject, String codeLine, List<Method> methodsList) {
        CouplingComplexity couplingComplexity = new CouplingComplexity();
        String cleanedCodeLine = MethodAndVariableStringPatternService.cleanStringLiterals(codeLine);

        if (couplingPatternService.isMethod(cleanedCodeLine)) {
            String methodName = couplingPatternService.getMethodName(cleanedCodeLine);
        }

        scopeLevel += MethodAndVariableStringPatternService.getNumberOpenCurlBrackets(cleanedCodeLine);
        scopeLevel -= MethodAndVariableStringPatternService.getNumberCloseCurlBrackets(cleanedCodeLine);

//        lineObject.setCouplingComplexity(couplingComplexity);
    }
}
