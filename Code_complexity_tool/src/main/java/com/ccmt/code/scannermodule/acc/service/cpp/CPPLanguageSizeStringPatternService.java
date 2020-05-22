package com.Sprint 2 Assessment_ ITPM_WD_01.scannermodule.acc.service.cpp;

import com.Sprint 2 Assessment_ ITPM_WD_01.scannermodule.acc.service.SizeStringPatternService;

public class CPPLanguageSizeStringPatternService extends SizeStringPatternService {
    @Override
    protected String getOrSeparatedKeywords() {
        return "asm|new|this|auto|enum|operator|throw|explicit|private|true|break|" +
                "export|protected|try|extern|public|typedef|catch|false|register|" +
                "typeid|reinterpret_cast|typename|class|return|union|const|friend|" +
                "short|unsigned|const_cast|goto|signed|using|continue|sizeof|virtual|" +
                "default|inline|static|void|delete|static_cast|volatile|long|struct|wchar_t|" +
                "mutable|dynamic_cast|namespace|template";
    }

    @Override
    protected String getOrSeparatedIdentifierPrefixes() {
        return "class|for|case";
    }
}

