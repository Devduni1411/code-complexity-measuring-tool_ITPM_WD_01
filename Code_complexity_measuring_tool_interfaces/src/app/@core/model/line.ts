import {VariableComplexity} from './variable-complexity';
import {MethodComplexity} from './method-complexity';
import {SizeComplexity} from './size-complexity';
import {ControlStructureComplexity} from './control-structure-complexity';
import {CouplingComplexity} from './coupling-complexity';
import {InheritanceComplexity} from './inheritance-complexity';

export interface Line {
  lineNo: number;
  data: string;
  cs: number;
  ctc: number;
  cnc: number;
  ci: number;
  cps: number;
  tw: number;
  cr: number;
  variableComplexity: VariableComplexity;
  methodComplexity: MethodComplexity;
  sizeComplexity: SizeComplexity;
  controlStructureComplexity: ControlStructureComplexity;
  couplingComplexity: CouplingComplexity;
  inheritanceComplexity: InheritanceComplexity;
}
