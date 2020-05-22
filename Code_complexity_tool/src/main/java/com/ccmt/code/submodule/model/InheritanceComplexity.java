package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "inheritance_complexity")
@EntityListeners(AuditingEntityListener.class)
public class InheritanceComplexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean aClass;
    private int noOfInheritances;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isaClass() {
        return aClass;
    }

    public void setaClass(boolean aClass) {
        this.aClass = aClass;
    }

    public int getNoOfInheritances() {
        return noOfInheritances;
    }

    public void setNoOfInheritances(int noOfInheritances) {
        this.noOfInheritances = noOfInheritances;
    }
}
