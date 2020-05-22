package com.ccmt.code.scannermodule.acc;

import com.ccmt.code.coremodule.model.Line;
import com.ccmt.code.coremodule.model.SizeComplexity;
import com.ccmt.code.scannermodule.acc.enums.Language;
import com.ccmt.code.scannermodule.acc.service.cpp.CPPLanguageSizeStringPatternService;
import com.ccmt.code.scannermodule.acc.service.java.JavaLanguageSizeStringPatternService;
import com.ccmt.code.scannermodule.acc.service.SizeStringPatternService;

public class SizeComplexityAnalyzer {
    private SizeStringPatternService sizeStringPatternService = new JavaLanguageSizeStringPatternService();

    public SizeComplexityAnalyzer(Language language) {
        if (language == Language.JAVA) {
            this.sizeStringPatternService = new JavaLanguageSizeStringPatternService();
        } else if (language == Language.CPP) {
            this.sizeStringPatternService = new CPPLanguageSizeStringPatternService();
        }
    }

    void analyze(Line lineObject, String codeLine) {
        SizeComplexity sizeComplexity = new SizeComplexity();

        sizeComplexity.setNumberOfStringLiterals(sizeStringPatternService.getNumberOfStringLiterals(codeLine));

        String cleanedCodeLine = sizeStringPatternService.cleanStringLiterals(codeLine);

        sizeComplexity.setNumberOfKeyWords(sizeStringPatternService.getNumberOfKeyWords(cleanedCodeLine));
        sizeComplexity.setNumberOfIdentifiers(sizeStringPatternService.getNumberOfIdentifiers(cleanedCodeLine));
        sizeComplexity.setNumberOfNumericalValues(sizeStringPatternService.getNumberOfNumericValues(cleanedCodeLine));
        sizeComplexity.setNumberOfOperators(sizeStringPatternService.getNumberOfOperators(cleanedCodeLine));

        lineObject.setSizeComplexity(sizeComplexity);
    }
}
