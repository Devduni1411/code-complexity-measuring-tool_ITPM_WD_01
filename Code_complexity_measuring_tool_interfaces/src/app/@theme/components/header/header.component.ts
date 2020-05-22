import {Component, OnDestroy, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {
  NbDialogService,
  NbMediaBreakpointsService,
  NbMenuService,
  NbSidebarService,
  NbThemeService, NbToastrService,
} from '@nebular/theme';

import {UserData} from '../../../@core/data/users';
import {LayoutService} from '../../../@core/utils';
import {filter, map, takeUntil} from 'rxjs/operators';
import {Subject} from 'rxjs';
import {UserApiService} from '../../../@core/service/api-service/user-api.service';
import {AuthService} from '../../../@core/service/auth.service';
import {User} from '../../../@core/model/user';
import {NbTokenService} from '@nebular/auth';
import {RedirectionService} from '../../../@core/service/redirection.service';
import {WeightService} from '../../../@core/service/weight.service';

@Component({
  selector: 'ngx-header',
  styleUrls: ['./header.component.scss'],
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit, OnDestroy {

  private destroy$: Subject<void> = new Subject<void>();
  userPictureOnly: boolean = false;
  user: User;

  themes = [
    {
      value: 'default',
      name: 'Light',
    },
    {
      value: 'dark',
      name: 'Dark',
    },
    {
      value: 'cosmic',
      name: 'Cosmic',
    },
    {
      value: 'corporate',
      name: 'Corporate',
    },
    {
      value: 'aquamarine',
      name: 'Aquamarine',
    },
  ];

  currentTheme = 'default';

  userMenu = [{title: 'Profile'}, {title: 'Log out'}];

  @ViewChild('info', {static: false}) info: TemplateRef<any>;
  @ViewChild('settings', {static: false}) settings: TemplateRef<any>;

  constructor(private sidebarService: NbSidebarService,
              private menuService: NbMenuService,
              private themeService: NbThemeService,
              private userService: UserData,
              private layoutService: LayoutService,
              private breakpointService: NbMediaBreakpointsService,
              private dialogService: NbDialogService,
              private toastrService: NbToastrService,
              private userApiService: UserApiService,
              private tokenService: NbTokenService,
              private redirectionService: RedirectionService,
              private nbMenuService: NbMenuService,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.changeTheme(this.currentTheme);
    this.currentTheme = this.themeService.currentTheme;

    this.userService.getUsers()
      .pipe(takeUntil(this.destroy$))
      .subscribe((users: any) => this.user = users.nick);

    const {xl} = this.breakpointService.getBreakpointsMap();
    this.themeService.onMediaQueryChange()
      .pipe(
        map(([, currentBreakpoint]) => currentBreakpoint.width < xl),
        takeUntil(this.destroy$),
      )
      .subscribe((isLessThanXl: boolean) => this.userPictureOnly = isLessThanXl);

    this.themeService.onThemeChange()
      .pipe(
        map(({name}) => name),
        takeUntil(this.destroy$),
      )
      .subscribe(themeName => this.currentTheme = themeName);

    this.navigateHome();
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  changeTheme(themeName: string) {
    this.themeService.changeTheme(themeName);
  }

  toggleSidebar(): boolean {
    this.sidebarService.toggle(true, 'menu-sidebar');
    this.layoutService.changeLayoutSize();

    return false;
  }

  navigateHome() {
    this.menuService.navigateHome();
    return false;
  }

  openInfo() {
    this.dialogService.open(this.info);
  }

  openSettings() {
    this.dialogService.open(this.settings);
  }

  weightsForSize: boolean = false;
  weightsForVariables: boolean = false;
  weightsForMethods: boolean = false;
  weightsForInheritance: boolean = false;
  weightsForCoupling: boolean = false;
  weightsForControlStructure: boolean = false;

  allFalse() {
    this.weightsForSize = false;
    this.weightsForVariables = false;
    this.weightsForMethods = false;
    this.weightsForInheritance = false;
    this.weightsForCoupling = false;
    this.weightsForControlStructure = false;
  }

  selectWeightsForSize() {
    this.allFalse();
    this.weightsForSize = true;
  }

  selectWeightsForVariables() {
    this.allFalse();
    this.weightsForVariables = true;
  }

  selectWeightsForMethods() {
    this.allFalse();
    this.weightsForMethods = true;
  }

  selectWeightsForInheritance() {
    this.allFalse();
    this.weightsForInheritance = true;
  }

  selectWeightsForCoupling() {
    this.allFalse();
    this.weightsForCoupling = true;
  }

  selectWeightsForControlStructure() {
    this.allFalse();
    this.weightsForControlStructure = true;
  }

  weightValuesForSize = {
    Keyword: 1,
    Identifier: 1,
    Operator: 1,
    NumericalValue: 1,
    StringLiteral: 1,
  };

  weightValuesForVariables = {
    GlobalVariable: 2,
    LocalVariable: 1,
    PrimitiveDataTypeVariable: 1,
    CompositeDataTypeVariable: 2,
  };

  weightValuesForMethods = {
    MethodWithAPrimitiveReturnType: 1,
    MethodWithACompositeReturnType: 2,
    MethodWithAVoidReturnType: 0,
    PrimitiveDataTypeParameter: 1,
    CompositeDataTypeParameter: 2,
  };

  weightValuesForInheritance = {
    no: 0,
    one: 1,
    two: 2,
    three: 3,
    more: 4,
  };

  weightValuesForCoupling = {
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

  weightValuesForControlStructure = {
    Aconditionalcontrolstructuresuchasaniforelseifcondition: 2,
    Aniterativecontrolstructuresuchasaforwhileordowhileloop: 3,
    Theswitchstatementinaswitchcasecontrolstructure: 2,
    Eachcasestatementinaswitchcasecontrolstructure: 1,
  };

  saveNotification() {
    WeightService.setWeightValuesForVariables(this.weightValuesForVariables);
    WeightService.setWeightValuesForMethods(this.weightValuesForMethods);
    WeightService.setWeightValuesForSize(this.weightValuesForSize);
    WeightService.setWeightValuesForControlStructure(this.weightValuesForControlStructure);
    WeightService.setWeightValuesForInheritance(this.weightValuesForInheritance);
    WeightService.setWeightValuesForCoupling(this.weightValuesForCoupling);
    this.toastrService.success('Values saved successfully!', 'Successful');
  }
}
