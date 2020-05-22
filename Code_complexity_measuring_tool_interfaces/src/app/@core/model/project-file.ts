import {Line} from './line';
import {CouplingComplexity} from './coupling-complexity';

export interface ProjectFile {
  relativePath: string;
  linesData: Line[];
  cp: number;
  couplingComplexity: CouplingComplexity;
}
