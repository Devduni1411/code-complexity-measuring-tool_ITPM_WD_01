package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_file")
@EntityListeners(AuditingEntityListener.class)
public class ProjectFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String relativePath;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Line> linesData;
    private int cp;
    @OneToOne(cascade = CascadeType.ALL)
    private CouplingComplexity couplingComplexity;

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public List<Line> getLinesData() {
        return linesData;
    }

    public void setLinesData(List<Line> linesData) {
        this.linesData = linesData;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CouplingComplexity getCouplingComplexity() {
        return couplingComplexity;
    }

    public void setCouplingComplexity(CouplingComplexity couplingComplexity) {
        this.couplingComplexity = couplingComplexity;
    }

    @Override
    public String toString() {
        return "ProjectFile{" +
                "relativePath='" + relativePath + '\'' +
                ", linesData=" + linesData +
                ", cp=" + cp +
                '}';
    }
}
