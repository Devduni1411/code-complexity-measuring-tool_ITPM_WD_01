package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "analysis")
@EntityListeners(AuditingEntityListener.class)
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long createdTime;
    private String projectKey;
    @OneToOne(cascade = CascadeType.ALL)
    private Project project;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id='" + id + '\'' +
                ", createdTime=" + createdTime +
                ", projectKey='" + projectKey + '\'' +
                ", project=" + project +
                '}';
    }
}
