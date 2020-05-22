package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "line")
@EntityListeners(AuditingEntityListener.class)
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int lineNo;
    private String data;
    private int cs;
    private int ctc;
    private int cnc;
    private int ci;
    private int cps;
    private int tw;
    private int cr;
    @OneToOne(cascade = CascadeType.ALL)
    private MethodComplexity methodComplexity;
    @OneToOne(cascade = CascadeType.ALL)
    private VariableComplexity variableComplexity;
    @OneToOne(cascade = CascadeType.ALL)
    private SizeComplexity sizeComplexity;
    @OneToOne(cascade = CascadeType.ALL)
    private ControlStructureComplexity controlStructureComplexity;
    @OneToOne(cascade = CascadeType.ALL)
    private CouplingComplexity couplingComplexity;
    @OneToOne(cascade = CascadeType.ALL)
    private InheritanceComplexity inheritanceComplexity;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public int getCs() {
        return cs;
    }

    public void setCs(int cs) {
        this.cs = cs;
    }

    public int getCtc() {
        return ctc;
    }

    public void setCtc(int ctc) {
        this.ctc = ctc;
    }

    public int getCnc() {
        return cnc;
    }

    public void setCnc(int cnc) {
        this.cnc = cnc;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getCps() {
        return cps;
    }

    public void setCps(int cps) {
        this.cps = cps;
    }

    public int getTw() {
        return tw;
    }

    public void setTw(int tw) {
        this.tw = tw;
    }

    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MethodComplexity getMethodComplexity() {
        return methodComplexity;
    }

    public void setMethodComplexity(MethodComplexity methodComplexity) {
        this.methodComplexity = methodComplexity;
    }

    public VariableComplexity getVariableComplexity() {
        return variableComplexity;
    }

    public void setVariableComplexity(VariableComplexity variableComplexity) {
        this.variableComplexity = variableComplexity;
    }

    public SizeComplexity getSizeComplexity() {
        return sizeComplexity;
    }

    public void setSizeComplexity(SizeComplexity sizeComplexity) {
        this.sizeComplexity = sizeComplexity;
    }

    public ControlStructureComplexity getControlStructureComplexity() {
        return controlStructureComplexity;
    }

    public void setControlStructureComplexity(ControlStructureComplexity controlStructureComplexity) {
        this.controlStructureComplexity = controlStructureComplexity;
    }

    public CouplingComplexity getCouplingComplexity() {
        return couplingComplexity;
    }

    public void setCouplingComplexity(CouplingComplexity couplingComplexity) {
        this.couplingComplexity = couplingComplexity;
    }

    public InheritanceComplexity getInheritanceComplexity() {
        return inheritanceComplexity;
    }

    public void setInheritanceComplexity(InheritanceComplexity inheritanceComplexity) {
        this.inheritanceComplexity = inheritanceComplexity;
    }

    @Override
    public String toString() {
        return "Line{" +
                "lineNo=" + lineNo +
                ", cs=" + cs +
                ", ctc=" + ctc +
                ", cnc=" + cnc +
                ", ci=" + ci +
                ", cps=" + cps +
                ", tw=" + tw +
                ", cr=" + cr +
                ", data=" + data +
                '}';
    }
}
