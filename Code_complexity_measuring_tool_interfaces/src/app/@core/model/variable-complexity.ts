export interface VariableComplexity {
  id: number;
  numberOfPrimitiveDataTypeVariables: number;
  numberOfCompositeDataTypeVariables: number;
  scopeLocal: number;
  scopeGlobal: number;
}
