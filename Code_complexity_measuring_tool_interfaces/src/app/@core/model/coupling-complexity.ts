export interface CouplingComplexity {
  id: number;
  regularMethodCallingAnotherRegularMethodInTheSameFile: number;
  regularMethodCallingAnotherRegularMethodInaDifferentFile: number;
  regularMethodCallingARecursiveMethodInTheSameFile: number;
  regularMethodCallingARecursiveMethodInaDifferentFile: number;
  recursiveMethodCallingAnotherRecursiveMethodInTheSameFile: number;
  recursiveMethodCallingAnotherRecursiveMethodInaDifferentFile: number;
  recursiveMethodCallingARegularMethodInTheSameFile: number;
  recursiveMethodCallingARegularMethodInaDifferentFile: number;
  regularMethodReferencingAGlobalVariableInTheSameFile: number;
  regularMethodReferencingAGlobalVariableInADifferentFile: number;
  recursiveMethodReferencingAGlobalVariableInTheSameFile: number;
  recursiveMethodReferencingAGlobalVariableInADifferentFile: number;
}
