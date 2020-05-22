package com.ccmt.code.submodule.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
@EntityListeners(AuditingEntityListener.class)
public class Project {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String projectKey;
   private String sourcePath;
   private String language;
   @OneToMany(cascade = CascadeType.ALL)
   private List<ProjectFile> files;
   private int cp;

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public List<ProjectFile> getFiles() {
      return files;
   }

   public void setFiles(List<ProjectFile> files) {
      this.files = files;
   }

   public String getProjectKey() {
      return projectKey;
   }

   public void setProjectKey(String projectKey) {
      this.projectKey = projectKey;
   }

   public String getSourcePath() {
      return sourcePath;
   }

   public void setSourcePath(String sourcePath) {
      this.sourcePath = sourcePath;
   }

   public String getLanguage() {
      return language;
   }

   public void setLanguage(String language) {
      this.language = language;
   }

   public int getCp() {
      return cp;
   }

   public void setCp(int cp) {
      this.cp = cp;
   }

   @Override
   public String toString() {
      return "Project{" +
            "projectKey='" + projectKey + '\'' +
            ", sourcePath='" + sourcePath + '\'' +
            ", language='" + language + '\'' +
            ", files=" + files +
            '}';
   }
}
