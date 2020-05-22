package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "control_structure_complexity")
@EntityListeners(AuditingEntityListener.class)
public class ControlStructureComplexity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int noOfIfs;
    private int noOfLoops;
    private int noOfSwitches;
    private int noOfCases;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNoOfIfs() {
        return noOfIfs;
    }

    public void setNoOfIfs(int noOfIfs) {
        this.noOfIfs = noOfIfs;
    }

    public int getNoOfLoops() {
        return noOfLoops;
    }

    public void setNoOfLoops(int noOfLoops) {
        this.noOfLoops = noOfLoops;
    }

    public int getNoOfSwitches() {
        return noOfSwitches;
    }

    public void setNoOfSwitches(int noOfSwitches) {
        this.noOfSwitches = noOfSwitches;
    }

    public int getNoOfCases() {
        return noOfCases;
    }

    public void setNoOfCases(int noOfCases) {
        this.noOfCases = noOfCases;
    }
}
