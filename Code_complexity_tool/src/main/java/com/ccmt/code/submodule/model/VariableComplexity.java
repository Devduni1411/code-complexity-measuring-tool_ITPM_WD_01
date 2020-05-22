package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "variable_complexity")
@EntityListeners(AuditingEntityListener.class)
public class VariableComplexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numberOfPrimitiveDataTypeVariables;
    private int numberOfCompositeDataTypeVariables;
    private int scopeLocal;
    private int scopeGlobal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfPrimitiveDataTypeVariables() {
        return numberOfPrimitiveDataTypeVariables;
    }

    public void setNumberOfPrimitiveDataTypeVariables(int numberOfPrimitiveDataTypeVariables) {
        this.numberOfPrimitiveDataTypeVariables = numberOfPrimitiveDataTypeVariables;
    }

    public int getNumberOfCompositeDataTypeVariables() {
        return numberOfCompositeDataTypeVariables;
    }

    public void setNumberOfCompositeDataTypeVariables(int numberOfCompositeDataTypeVariables) {
        this.numberOfCompositeDataTypeVariables = numberOfCompositeDataTypeVariables;
    }

    public int getScopeLocal() {
        return scopeLocal;
    }

    public void setScopeLocal(int scopeLocal) {
        this.scopeLocal = scopeLocal;
    }

    public int getScopeGlobal() {
        return scopeGlobal;
    }

    public void setScopeGlobal(int scopeGlobal) {
        this.scopeGlobal = scopeGlobal;
    }
}
