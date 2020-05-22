import {Injectable} from '@angular/core';
import {VariableComplexity} from '../model/variable-complexity';
import {MethodComplexity} from '../model/method-complexity';
import {SizeComplexity} from '../model/size-complexity';
import {ControlStructureComplexity} from '../model/control-structure-complexity';
import {InheritanceComplexity} from '../model/inheritance-complexity';
import {CouplingComplexity} from '../model/coupling-complexity';

@Injectable({
  providedIn: 'root',
})
export class WeightService {
  private static _weightValuesForSize = {
    Keyword: 1,
    Identifier: 1,
    Operator: 1,
    NumericalValue: 1,
    StringLiteral: 1,
  };

  private static _weightValuesForVariables = {
    GlobalVariable: 2,
    LocalVariable: 1,
    PrimitiveDataTypeVariable: 1,
    CompositeDataTypeVariable: 2,
  };

  private static _weightValuesForMethods = {
    MethodWithAPrimitiveReturnType: 1,
    MethodWithACompositeReturnType: 2,
    MethodWithAVoidReturnType: 0,
    PrimitiveDataTypeParameter: 1,
    CompositeDataTypeParameter: 2,
  };

  private static _weightValuesForInheritance = {
    no: 0,
    one: 1,
    two: 2,
    three: 3,
    more: 4,
  };

  private static _weightValuesForCoupling = {
    Arecursivecall: 2,
    Aregularmethodcallinganotherregularmethodinthesamefile: 2,
    Aregularmethodcallinganotherregularmethodinadifferentfile: 3,
    Aregularmethodcallingarecursivemethodinthesamefile: 3,
    Aregularmethodcallingarecursivemethodinadifferentfile: 4,
    Arecursivemethodcallinganotherrecursivemethodinthesamefile: 4,
    Arecursivemethodcallinganotherrecursivemethodinadifferentfile: 5,
    Arecursivemethodcallingaregularmethodinthesamefile: 3,
    Arecursivemethodcallingaregularmethodinadifferentfile: 4,
    Aregularmethodreferencingaglobalvariableinthesamefile: 1,
    Aregularmethodreferencingaglobalvariableinadifferentfile: 2,
    Arecursivemethodreferencingaglobalvariableinthesamefile: 1,
    Arecursivemethodreferencingaglobalvariableinadifferentfile: 2,
  };

  private static _weightValuesForControlStructure = {
    Aconditionalcontrolstructuresuchasaniforelseifcondition: 2,
    Aniterativecontrolstructuresuchasaforwhileordowhileloop: 3,
    Theswitchstatementinaswitchcasecontrolstructure: 2,
    Eachcasestatementinaswitchcasecontrolstructure: 1,
  };

  constructor() {
  }

  static setWeightValuesForVariables(weightValuesForVariables) {
    this._weightValuesForVariables.GlobalVariable = weightValuesForVariables.GlobalVariable;
    this._weightValuesForVariables.LocalVariable = weightValuesForVariables.LocalVariable;
    this._weightValuesForVariables.PrimitiveDataTypeVariable = weightValuesForVariables.PrimitiveDataTypeVariable;
    this._weightValuesForVariables.CompositeDataTypeVariable = weightValuesForVariables.CompositeDataTypeVariable;
  }


  static setWeightValuesForMethods(value:
                                     {
                                       MethodWithACompositeReturnType: number;
                                       CompositeDataTypeParameter: number;
                                       MethodWithAPrimitiveReturnType: number;
                                       PrimitiveDataTypeParameter: number;
                                       MethodWithAVoidReturnType: number
                                     }) {
    this._weightValuesForMethods = value;
  }

  static setWeightValuesForSize(value:
                                  {
                                    Operator: number;
                                    Identifier: number;
                                    Keyword: number;
                                    StringLiteral: number;
                                    NumericalValue: number
                                  }) {
    this._weightValuesForSize = value;
  }


  static setWeightValuesForControlStructure(value:
                                              { Aniterativecontrolstructuresuchasaforwhileordowhileloop: number;
                                              Theswitchstatementinaswitchcasecontrolstructure: number;
                                              Eachcasestatementinaswitchcasecontrolstructure: number;
                                              Aconditionalcontrolstructuresuchasaniforelseifcondition: number }) {
    this._weightValuesForControlStructure = value;
  }


  static setWeightValuesForInheritance(value:
                                         { no: number;
                                         more: number;
                                         one: number;
                                         two: number;
                                         three: number }) {
    this._weightValuesForInheritance = value;
  }

  static setWeightValuesForCoupling(value:
                                      { Arecursivemethodreferencingaglobalvariableinthesamefile: number;
                                      Arecursivemethodcallinganotherrecursivemethodinadifferentfile: number;
                                      Aregularmethodcallinganotherregularmethodinadifferentfile: number;
                                      Arecursivecall: number;
                                      Aregularmethodreferencingaglobalvariableinthesamefile: number;
                                      Aregularmethodreferencingaglobalvariableinadifferentfile: number;
                                      Aregularmethodcallingarecursivemethodinadifferentfile: number;
                                      Aregularmethodcallinganotherregularmethodinthesamefile: number;
                                      Arecursivemethodcallingaregularmethodinadifferentfile: number;
                                      Arecursivemethodcallinganotherrecursivemethodinthesamefile: number;
                                      Arecursivemethodreferencingaglobalvariableinadifferentfile: number;
                                      Aregularmethodcallingarecursivemethodinthesamefile: number;
                                      Arecursivemethodcallingaregularmethodinthesamefile: number }) {
    this._weightValuesForCoupling = value;
  }

  static get weightValuesForVariables():
    {
      GlobalVariable: number;
      PrimitiveDataTypeVariable: number;
      CompositeDataTypeVariable: number;
      LocalVariable: number
    } {
    return this._weightValuesForVariables;
  }

  static get weightValuesForMethods():
    {
      MethodWithACompositeReturnType: number;
      CompositeDataTypeParameter: number;
      MethodWithAPrimitiveReturnType: number;
      PrimitiveDataTypeParameter: number;
      MethodWithAVoidReturnType: number
    } {
    return this._weightValuesForMethods;
  }

  static getComplexityDueToVariable(variableComplexity: VariableComplexity): number {
    let Wvs = 0;
    if (variableComplexity.scopeGlobal === 1) Wvs = this._weightValuesForVariables.GlobalVariable;
    else if (variableComplexity.scopeLocal === 1) Wvs = this._weightValuesForVariables.LocalVariable;

    return Wvs * (
      this._weightValuesForVariables.CompositeDataTypeVariable * variableComplexity.numberOfCompositeDataTypeVariables
      +
      this._weightValuesForVariables.PrimitiveDataTypeVariable * variableComplexity.numberOfPrimitiveDataTypeVariables);
  }

  static getComplexityDueToMethod(methodComplexity: MethodComplexity): number {
    let Wmrt: number = 0;
    if (methodComplexity.returnType === 0) Wmrt = this._weightValuesForMethods.MethodWithAVoidReturnType;
    else if (methodComplexity.returnType === 1) Wmrt = this._weightValuesForMethods.MethodWithAPrimitiveReturnType;
    else if (methodComplexity.returnType === -1) Wmrt = this._weightValuesForMethods.MethodWithACompositeReturnType;
    return ((+Wmrt) +
      (this._weightValuesForMethods.PrimitiveDataTypeParameter * methodComplexity.numberOfPrimitiveDataTypeParameters)
      +
      (this._weightValuesForMethods.CompositeDataTypeParameter * methodComplexity.numberOfCompositeDataTypeParameters));
  }

  static getComplexityDueToSize(sizeComplexity: SizeComplexity) {
    return (sizeComplexity.numberOfIdentifiers * this._weightValuesForSize.Identifier) +
      (sizeComplexity.numberOfKeyWords * this._weightValuesForSize.Keyword) +
      (sizeComplexity.numberOfNumericalValues * this._weightValuesForSize.NumericalValue) +
      (sizeComplexity.numberOfOperators * this._weightValuesForSize.Operator) +
      (sizeComplexity.numberOfStringLiterals * this._weightValuesForSize.StringLiteral);
  }

  static getComplexityDueToControlStructure(controlStructureComplexity: ControlStructureComplexity): number {
    return (this._weightValuesForControlStructure.Eachcasestatementinaswitchcasecontrolstructure
                * controlStructureComplexity.noOfCases) +
      (this._weightValuesForControlStructure.Theswitchstatementinaswitchcasecontrolstructure
                * controlStructureComplexity.noOfSwitches) +
      (this._weightValuesForControlStructure.Aniterativecontrolstructuresuchasaforwhileordowhileloop
                * controlStructureComplexity.noOfLoops) +
      (this._weightValuesForControlStructure.Aconditionalcontrolstructuresuchasaniforelseifcondition
                * controlStructureComplexity.noOfIfs);
  }

  static getComplexityDueInheritance(inheritanceComplexity: InheritanceComplexity): number {
    if (inheritanceComplexity && inheritanceComplexity.aClass) {
      if (inheritanceComplexity.noOfInheritances && (inheritanceComplexity.noOfInheritances > 3)) {
        return this._weightValuesForInheritance.more;
      } else if (inheritanceComplexity.noOfInheritances && (inheritanceComplexity.noOfInheritances === 3)) {
        return this._weightValuesForInheritance.three;
      } else if (inheritanceComplexity.noOfInheritances && (inheritanceComplexity.noOfInheritances === 2)) {
        return this._weightValuesForInheritance.two;
      } else if (inheritanceComplexity.noOfInheritances && (inheritanceComplexity.noOfInheritances === 1)) {
        return this._weightValuesForInheritance.one;
      } else if (inheritanceComplexity.noOfInheritances && (inheritanceComplexity.noOfInheritances === 0)) {
        return this._weightValuesForInheritance.no;
      }
    }
    return 0;
  }

  static getComplexityDueToCoupling(couplingComplexity: CouplingComplexity): number {
    return (couplingComplexity.regularMethodCallingAnotherRegularMethodInTheSameFile *
      this._weightValuesForCoupling.Aregularmethodcallinganotherregularmethodinthesamefile) +
    (couplingComplexity.regularMethodCallingAnotherRegularMethodInaDifferentFile *
      this._weightValuesForCoupling.Aregularmethodcallinganotherregularmethodinadifferentfile) +
    (couplingComplexity.regularMethodCallingARecursiveMethodInTheSameFile *
      this._weightValuesForCoupling.Aregularmethodcallingarecursivemethodinthesamefile) +
    (couplingComplexity.regularMethodCallingARecursiveMethodInaDifferentFile *
      this._weightValuesForCoupling.Aregularmethodcallingarecursivemethodinadifferentfile) +
    (couplingComplexity.recursiveMethodCallingAnotherRecursiveMethodInTheSameFile *
      this._weightValuesForCoupling.Arecursivemethodcallinganotherrecursivemethodinthesamefile) +
    (couplingComplexity.recursiveMethodCallingAnotherRecursiveMethodInaDifferentFile *
      this._weightValuesForCoupling.Arecursivemethodcallinganotherrecursivemethodinadifferentfile) +
    (couplingComplexity.recursiveMethodCallingARegularMethodInTheSameFile *
      this._weightValuesForCoupling.Arecursivemethodcallingaregularmethodinthesamefile) +
    (couplingComplexity.recursiveMethodCallingARegularMethodInaDifferentFile *
      this._weightValuesForCoupling.Arecursivemethodcallingaregularmethodinadifferentfile) +
    (couplingComplexity.regularMethodReferencingAGlobalVariableInTheSameFile *
      this._weightValuesForCoupling.Aregularmethodreferencingaglobalvariableinthesamefile) +
    (couplingComplexity.regularMethodReferencingAGlobalVariableInADifferentFile *
      this._weightValuesForCoupling.Aregularmethodreferencingaglobalvariableinadifferentfile) +
    (couplingComplexity.recursiveMethodReferencingAGlobalVariableInTheSameFile *
      this._weightValuesForCoupling.Arecursivemethodreferencingaglobalvariableinthesamefile) +
    (couplingComplexity.recursiveMethodReferencingAGlobalVariableInADifferentFile *
      this._weightValuesForCoupling.Arecursivemethodreferencingaglobalvariableinadifferentfile);
  }
}
