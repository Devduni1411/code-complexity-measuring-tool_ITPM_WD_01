import {ProjectFile} from './project-file';

export interface Project {
  projectKey: string;
  sourcePath: string;
  language: string;
  files: ProjectFile[];
  cp: number;
}
