package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "size_complexity")
@EntityListeners(AuditingEntityListener.class)
public class SizeComplexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numberOfKeyWords;
    private int numberOfIdentifiers;
    private int numberOfOperators;
    private int numberOfNumericalValues;
    private int numberOfStringLiterals;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfKeyWords() {
        return numberOfKeyWords;
    }

    public void setNumberOfKeyWords(int numberOfKeyWords) {
        this.numberOfKeyWords = numberOfKeyWords;
    }

    public int getNumberOfIdentifiers() {
        return numberOfIdentifiers;
    }

    public void setNumberOfIdentifiers(int numberOfIdentifiers) {
        this.numberOfIdentifiers = numberOfIdentifiers;
    }

    public int getNumberOfOperators() {
        return numberOfOperators;
    }

    public void setNumberOfOperators(int numberOfOperators) {
        this.numberOfOperators = numberOfOperators;
    }

    public int getNumberOfNumericalValues() {
        return numberOfNumericalValues;
    }

    public void setNumberOfNumericalValues(int numberOfNumericalValues) {
        this.numberOfNumericalValues = numberOfNumericalValues;
    }

    public int getNumberOfStringLiterals() {
        return numberOfStringLiterals;
    }

    public void setNumberOfStringLiterals(int numberOfStringLiterals) {
        this.numberOfStringLiterals = numberOfStringLiterals;
    }
}
