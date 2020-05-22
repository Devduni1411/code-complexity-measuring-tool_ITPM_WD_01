import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CoreUrlService} from '../url-service/core-url.service';
import {Project} from '../../model/project';

@Injectable({
  providedIn: 'root',
})
export class ApiService {

  PROJECTS_URL = `${this.coreUrlService.BASE_URL}/projects`;
  SCAN_URL = `${this.coreUrlService.BASE_URL}/scan`;

  constructor(private coreUrlService: CoreUrlService, private  http: HttpClient) { }

  getProjectByKey(key: string): Observable<Project> {
    return this.http.get<Project>(this.PROJECTS_URL + '/' + key);
  }

  getProjectHistoryByKey(key: string): Observable<Project> {
    return this.http.get<Project>(this.PROJECTS_URL + '/' + key + '/history');
  }

  getAllProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.PROJECTS_URL);
  }

  newScan(projectKey: string, sourcePath: string): Observable<boolean> {
    const params = new HttpParams()
      .set('project-key', projectKey)
      .set('source-path', sourcePath);
    return this.http.get<boolean>(this.SCAN_URL, {params: params});
  }

}
