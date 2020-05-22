import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {NbDialogService, NbToastrService} from '@nebular/theme';
import {ApiService} from '../../@core/service/api-service/api.service';
import {ErrorHandlingService} from '../../@core/service/error-handling.service';
import {Project} from '../../@core/model/project';
import {ProjectFile} from '../../@core/model/project-file';
import {Line} from '../../@core/model/line';
import {WeightService} from '../../@core/service/weight.service';
import {VariableComplexity} from '../../@core/model/variable-complexity';
import {MethodComplexity} from '../../@core/model/method-complexity';
import {SizeComplexity} from '../../@core/model/size-complexity';
import {ControlStructureComplexity} from '../../@core/model/control-structure-complexity';
import {InheritanceComplexity} from '../../@core/model/inheritance-complexity';
import {CouplingComplexity} from '../../@core/model/coupling-complexity';

class LineMock {
  sizeComplexity: number;
  ci: number;
  cnc: number;
  cps: number;
  cr: number;
  cs: number;
  ctc: number;
  data: number;
  lineNo: number;
  tw: number;
  methodComplexity: number;
  variableComplexity: number;
  controlStructureComplexity: number;
  inheritanceComplexity: number;
  couplingComplexity: number;
}

@Component({
  selector: 'ngx-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  @ViewChild('complexityViewer', {static: false}) complexityViewer: TemplateRef<any>;
  @ViewChild('projectComplexityViewer', {static: false}) projectComplexityViewer: TemplateRef<any>;
  allProjects: Project[] = [];
  sourcePathForNewScan: string = '';
  projectKeyForNewScan: string = '';
  loadingNewScan: boolean = false;
  selectedProject: Project = null;
  selectedFile: ProjectFile = null;
  filters = {
    showCs: true,
    showCv: true,
    showCm: true,
    showCi: true,
    showCcp: true,
    showCcs: true,
  };
  seeFilters: boolean = false;
  private selectedFileSizeComplexity: number;
  private selectedFileVariableComplexity: number;
  private selectedFileMethodComplexity: number;

  constructor(private dialogService: NbDialogService,
              private toastrService: NbToastrService,
              private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.getAllProjects();
  }

  getAllProjects() {
    this.apiService.getAllProjects().subscribe(
      res => {
        this.allProjects = res;
      },
      err => {
        ErrorHandlingService.handle(err, this.toastrService);
      },
    );
  }

  startNewScan() {
    this.loadingNewScan = true;
    this.apiService.newScan(this.projectKeyForNewScan, this.sourcePathForNewScan).subscribe(
      res => {
        if (res) {
          this.toastrService.success('Successfully scanned the project', 'Successful!');
          this.sourcePathForNewScan = '';
          this.projectKeyForNewScan = '';
        } else {
          this.toastrService.danger('Scanned failed', 'Failed!');
        }
        this.getAllProjects();
        this.loadingNewScan = false;
      },
      err => {
        ErrorHandlingService.handle(err, this.toastrService);
        this.loadingNewScan = false;
      },
    );
  }

  selectProject(project: Project) {
    this.selectedProject = project;
  }

  selectFile(file: ProjectFile) {
    this.selectedFile = file;
  }

  getFileNameFromPath(relativePath: string) {
    const pieces = relativePath.split(/[\s\\]+/);
    return pieces[pieces.length - 1];
  }

  openComplexityViewerForFile(file: ProjectFile) {
    this.selectFile(file);
    this.dialogService.open(this.complexityViewer);
  }

  selectOptions(showCs: boolean, showCv: boolean, showCm: boolean,
                showCi: boolean, showCcp: boolean, showCcs: boolean) {
    this.filters.showCs = showCs;
    this.filters.showCv = showCv;
    this.filters.showCm = showCm;
    this.filters.showCi = showCi;
    this.filters.showCcp = showCcp;
    this.filters.showCcs = showCcs;
  }

  selectCsOnly() {
    this.selectOptions(true, false, false, false, false, false);
  }

  selectCvOnly() {
    this.selectOptions(false, true, false, false, false, false);
  }

  selectCmOnly() {
    this.selectOptions(false, false, true, false, false, false);
  }

  selectCiOnly() {
    this.selectOptions(false, false, false, true, false, false);
  }

  selectCcpOnly() {
    this.selectOptions(false, false, false, false, true, false);
  }

  selectCcsOnly() {
    this.selectOptions(false, false, false, false, false, true);
  }

  selectAllFilters() {
    this.selectOptions(true, true, true, true, true, true);
  }

  getFileSummary(file: ProjectFile): LineMock {
    const summaryLine: LineMock = {
      controlStructureComplexity: 0, couplingComplexity: 0, inheritanceComplexity: 0,
      sizeComplexity: 0, ci: 0, cnc: 0, cps: 0, cr: 0, cs: 0, ctc: 0, data: 0, lineNo: 0, tw: 0,
      methodComplexity: 0, variableComplexity: 0,
    };
    this.selectedFileSizeComplexity = 0;
    for (const line of file.linesData) {
      if (line) {
        summaryLine.cs += line.cs;
        summaryLine.ctc += line.ctc;
        summaryLine.cnc += line.cnc;
        summaryLine.ci += line.ci;
        summaryLine.cps += line.cps;
        summaryLine.tw += line.tw;
        summaryLine.cr += line.cr;
        summaryLine.sizeComplexity += this.getSizeComplexity(line.sizeComplexity);
        summaryLine.variableComplexity += this.getVariableComplexity(line.variableComplexity);
        summaryLine.methodComplexity += this.getMethodComplexity(line.methodComplexity);
        summaryLine.controlStructureComplexity += this.getControlStructureComplexity(line.controlStructureComplexity);
        summaryLine.inheritanceComplexity += this.getInheritanceComplexity(line.inheritanceComplexity);
      }
    }
    summaryLine.couplingComplexity = this.getCouplingComplexity(file.couplingComplexity);
    return summaryLine;
  }

  isAllSelected(): boolean {
    return false;
    // return this.filters.showCs &&
    //   this.filters.showCv &&
    //   this.filters.showCm &&
    //   this.filters.showCi &&
    //   this.filters.showCcp &&
    //   this.filters.showCcs;
  }

  getVariableComplexity(variableComplexity: VariableComplexity): number {
    if (variableComplexity)
      return WeightService.getComplexityDueToVariable(variableComplexity);
    else return 0;
  }

  getMethodComplexity(methodComplexity: MethodComplexity): number {
    if (methodComplexity)
      return WeightService.getComplexityDueToMethod(methodComplexity);
    else return 0;
  }

  getSizeComplexity(sizeComplexity: SizeComplexity): number {
    if (sizeComplexity)
      return WeightService.getComplexityDueToSize(sizeComplexity);
    else return 0;
  }

  getControlStructureComplexity(controlStructureComplexity: ControlStructureComplexity): number {
    if (controlStructureComplexity)
      return WeightService.getComplexityDueToControlStructure(controlStructureComplexity);
    else return 0;
  }

  getInheritanceComplexity(inheritanceComplexity: InheritanceComplexity): number {
    if (inheritanceComplexity)
      return WeightService.getComplexityDueInheritance(inheritanceComplexity);
    else return 0;
  }

  getCouplingComplexity(couplingComplexity: CouplingComplexity): number {
    if (couplingComplexity)
      return WeightService.getComplexityDueToCoupling(couplingComplexity);
    else return 0;
  }

  calculateProjectComplexity(): number {
    let totalComplexity: number = 0;

    for (const file of this.selectedProject.files) {
      for (const line of file.linesData) {
        totalComplexity += this.getSizeComplexity(line.sizeComplexity);
        totalComplexity += this.getVariableComplexity(line.variableComplexity);
        totalComplexity += this.getMethodComplexity(line.methodComplexity);
        totalComplexity += this.getInheritanceComplexity(line.inheritanceComplexity);
        totalComplexity += this.getControlStructureComplexity(line.controlStructureComplexity);
      }
      totalComplexity += this.getCouplingComplexity(file.couplingComplexity);
    }

    return totalComplexity;
  }

  openProjectComplexity() {
    this.dialogService.open(this.projectComplexityViewer);
  }
}
