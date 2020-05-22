package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "coupling_complexity")
@EntityListeners(AuditingEntityListener.class)
public class CouplingComplexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int regularMethodCallingAnotherRegularMethodInTheSameFile;
    private int regularMethodCallingAnotherRegularMethodInaDifferentFile;
    private int regularMethodCallingARecursiveMethodInTheSameFile;
    private int regularMethodCallingARecursiveMethodInaDifferentFile;
    private int recursiveMethodCallingAnotherRecursiveMethodInTheSameFile;
    private int recursiveMethodCallingAnotherRecursiveMethodInaDifferentFile;
    private int recursiveMethodCallingARegularMethodInTheSameFile;
    private int recursiveMethodCallingARegularMethodInaDifferentFile;
    private int regularMethodReferencingAGlobalVariableInTheSameFile;
    private int regularMethodReferencingAGlobalVariableInADifferentFile;
    private int recursiveMethodReferencingAGlobalVariableInTheSameFile;
    private int recursiveMethodReferencingAGlobalVariableInADifferentFile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //getters setters
    public int getRegularMethodCallingAnotherRegularMethodInTheSameFile() {
        return regularMethodCallingAnotherRegularMethodInTheSameFile;
    }

    public void setRegularMethodCallingAnotherRegularMethodInTheSameFile(
            int regularMethodCallingAnotherRegularMethodInTheSameFile) {
        this.regularMethodCallingAnotherRegularMethodInTheSameFile
                = regularMethodCallingAnotherRegularMethodInTheSameFile;
    }

    public int getRegularMethodCallingAnotherRegularMethodInaDifferentFile() {
        return regularMethodCallingAnotherRegularMethodInaDifferentFile;
    }

    public void setRegularMethodCallingAnotherRegularMethodInaDifferentFile(
            int regularMethodCallingAnotherRegularMethodInaDifferentFile) {
        this.regularMethodCallingAnotherRegularMethodInaDifferentFile
                = regularMethodCallingAnotherRegularMethodInaDifferentFile;
    }

    public int getRegularMethodCallingARecursiveMethodInTheSameFile() {
        return regularMethodCallingARecursiveMethodInTheSameFile;
    }

    public void setRegularMethodCallingARecursiveMethodInTheSameFile(
            int regularMethodCallingARecursiveMethodInTheSameFile) {
        this.regularMethodCallingARecursiveMethodInTheSameFile
                = regularMethodCallingARecursiveMethodInTheSameFile;
    }

    public int getRegularMethodCallingARecursiveMethodInaDifferentFile() {
        return regularMethodCallingARecursiveMethodInaDifferentFile;
    }

    public void setRegularMethodCallingARecursiveMethodInaDifferentFile(
            int regularMethodCallingARecursiveMethodInaDifferentFile) {
        this.regularMethodCallingARecursiveMethodInaDifferentFile
                = regularMethodCallingARecursiveMethodInaDifferentFile;
    }

    public int getRecursiveMethodCallingAnotherRecursiveMethodInTheSameFile() {
        return recursiveMethodCallingAnotherRecursiveMethodInTheSameFile;
    }

    public void setRecursiveMethodCallingAnotherRecursiveMethodInTheSameFile(
            int recursiveMethodCallingAnotherRecursiveMethodInTheSameFile) {
        this.recursiveMethodCallingAnotherRecursiveMethodInTheSameFile
                = recursiveMethodCallingAnotherRecursiveMethodInTheSameFile;
    }

    public int getRecursiveMethodCallingAnotherRecursiveMethodInaDifferentFile() {
        return recursiveMethodCallingAnotherRecursiveMethodInaDifferentFile;
    }

    public void setRecursiveMethodCallingAnotherRecursiveMethodInaDifferentFile(
            int recursiveMethodCallingAnotherRecursiveMethodInaDifferentFile) {
        this.recursiveMethodCallingAnotherRecursiveMethodInaDifferentFile
                = recursiveMethodCallingAnotherRecursiveMethodInaDifferentFile;
    }

    public int getRecursiveMethodCallingARegularMethodInTheSameFile() {
        return recursiveMethodCallingARegularMethodInTheSameFile;
    }

    public void setRecursiveMethodCallingARegularMethodInTheSameFile(
            int recursiveMethodCallingARegularMethodInTheSameFile) {
        this.recursiveMethodCallingARegularMethodInTheSameFile
                = recursiveMethodCallingARegularMethodInTheSameFile;
    }

    public int getRecursiveMethodCallingARegularMethodInaDifferentFile() {
        return recursiveMethodCallingARegularMethodInaDifferentFile;
    }

    public void setRecursiveMethodCallingARegularMethodInaDifferentFile(
            int recursiveMethodCallingARegularMethodInaDifferentFile) {
        this.recursiveMethodCallingARegularMethodInaDifferentFile
                = recursiveMethodCallingARegularMethodInaDifferentFile;
    }

    public int getRegularMethodReferencingAGlobalVariableInTheSameFile() {
        return regularMethodReferencingAGlobalVariableInTheSameFile;
    }

    public void setRegularMethodReferencingAGlobalVariableInTheSameFile(
            int regularMethodReferencingAGlobalVariableInTheSameFile) {
        this.regularMethodReferencingAGlobalVariableInTheSameFile
                = regularMethodReferencingAGlobalVariableInTheSameFile;
    }

    public int getRegularMethodReferencingAGlobalVariableInADifferentFile() {
        return regularMethodReferencingAGlobalVariableInADifferentFile;
    }

    public void setRegularMethodReferencingAGlobalVariableInADifferentFile(
            int regularMethodReferencingAGlobalVariableInADifferentFile) {
        this.regularMethodReferencingAGlobalVariableInADifferentFile
                = regularMethodReferencingAGlobalVariableInADifferentFile;
    }

    public int getRecursiveMethodReferencingAGlobalVariableInTheSameFile() {
        return recursiveMethodReferencingAGlobalVariableInTheSameFile;
    }

    public void setRecursiveMethodReferencingAGlobalVariableInTheSameFile(
            int recursiveMethodReferencingAGlobalVariableInTheSameFile) {
        this.recursiveMethodReferencingAGlobalVariableInTheSameFile
                = recursiveMethodReferencingAGlobalVariableInTheSameFile;
    }

    public int getRecursiveMethodReferencingAGlobalVariableInADifferentFile() {
        return recursiveMethodReferencingAGlobalVariableInADifferentFile;
    }

    public void setRecursiveMethodReferencingAGlobalVariableInADifferentFile(
            int recursiveMethodReferencingAGlobalVariableInADifferentFile) {
        this.recursiveMethodReferencingAGlobalVariableInADifferentFile
                = recursiveMethodReferencingAGlobalVariableInADifferentFile;
    }
}
