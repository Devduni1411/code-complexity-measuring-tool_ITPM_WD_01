import {Project} from './project';

export interface Analysis {
  id: number;
  createdTime: number;
  projectKey: string;
  project: Project;
}
