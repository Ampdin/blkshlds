import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRouteSnapshot, NavigationEnd } from '@angular/router';
import {
    NbMediaBreakpoint,
    NbMediaBreakpointsService,
    NbMenuItem,
    NbMenuService,
    NbSidebarService,
    NbThemeService,
} from '@nebular/theme';
import { StateService } from '../../@core/data/state.service';
import { Subscription } from 'rxjs/Subscription';
import 'rxjs/add/operator/withLatestFrom';
import 'rxjs/add/operator/delay';
import { JhiLanguageHelper } from '../../shared';

@Component({
    selector: 'jhi-main',
    styleUrls: ['./main.component.scss'],
    template: '<router-outlet></router-outlet>',
})
export class JhiMainComponent implements OnInit {
    layout: any = {};
    sidebar: any = {};

    protected layoutState$: Subscription;
    protected sidebarState$: Subscription;
    protected menuClick$: Subscription;

    subMenu: NbMenuItem[] = [
        {
            title: 'PAGE LEVEL MENU',
            group: true,
        },
        {
            title: 'Classes',
            icon: 'ion ion-android-radio-button-off',
            link: '/pages/ui-features/book',
        },
        {
            title: 'Grid',
            icon: 'ion ion-android-radio-button-off',
            link: '/pages/ui-features/grid',
        },
        {
            title: 'Icons',
            icon: 'ion ion-android-radio-button-off',
            link: '/pages/ui-features/icons',
        },
        {
            title: 'Modals',
            icon: 'ion ion-android-radio-button-off',
            link: '/pages/ui-features/modals',
        },
        {
            title: 'Typography',
            icon: 'ion ion-android-radio-button-off',
            link: '/pages/ui-features/typography',
        },
        {
            title: 'Animated Searches',
            icon: 'ion ion-android-radio-button-off',
            link: '/pages/ui-features/search-fields',
        },
        {
            title: 'Tabs',
            icon: 'ion ion-android-radio-button-off',
            link: '/pages/ui-features/tabs',
        },
    ];

    constructor(
        private jhiLanguageHelper: JhiLanguageHelper,
        private router: Router,
        protected stateService: StateService,
        protected menuService: NbMenuService,
        protected themeService: NbThemeService,
        protected bpService: NbMediaBreakpointsService,
        protected sidebarService: NbSidebarService
    ) {
        this.layoutState$ = this.stateService.onLayoutState()
            .subscribe((layout: string) => this.layout = layout);

        this.sidebarState$ = this.stateService.onSidebarState()
            .subscribe((sidebar: string) => {
                this.sidebar = sidebar;
            });

        const isBp = this.bpService.getByName('is');
        this.menuClick$ = this.menuService.onItemSelect()
            .withLatestFrom(this.themeService.onMediaQueryChange())
            .delay(20)
            .subscribe(([item, [bpFrom, bpTo]]: [any, [NbMediaBreakpoint, NbMediaBreakpoint]]) => {

                if (bpTo.width <= isBp.width) {
                    this.sidebarService.collapse('menu-sidebar');
                }
            });
    }

    private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
        let title: string = (routeSnapshot.data && routeSnapshot.data['pageTitle']) ? routeSnapshot.data['pageTitle'] : 'barbicanApp';
        if (routeSnapshot.firstChild) {
            title = this.getPageTitle(routeSnapshot.firstChild) || title;
        }
        return title;
    }

    ngOnInit() {
        this.router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) {
                this.jhiLanguageHelper.updateTitle(this.getPageTitle(this.router.routerState.snapshot.root));
            }
        });
    }
}
