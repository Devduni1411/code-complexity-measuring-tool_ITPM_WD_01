package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "method_complexity")
@EntityListeners(AuditingEntityListener.class)
public class MethodComplexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numberOfPrimitiveDataTypeParameters;
    private int numberOfCompositeDataTypeParameters;
    private int returnType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfPrimitiveDataTypeParameters() {
        return numberOfPrimitiveDataTypeParameters;
    }

    public void setNumberOfPrimitiveDataTypeParameters(int numberOfPrimitiveDataTypeParameters) {
        this.numberOfPrimitiveDataTypeParameters = numberOfPrimitiveDataTypeParameters;
    }

    public int getNumberOfCompositeDataTypeParameters() {
        return numberOfCompositeDataTypeParameters;
    }

    public void setNumberOfCompositeDataTypeParameters(int numberOfCompositeDataTypeParameters) {
        this.numberOfCompositeDataTypeParameters = numberOfCompositeDataTypeParameters;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }
}
