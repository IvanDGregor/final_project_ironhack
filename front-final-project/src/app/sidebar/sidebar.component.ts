import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

declare interface RouteInfo {
  path: string;
  title: string;
  rtlTitle: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  {
    path: '/dashboard',
    title: 'Home',
    rtlTitle: 'لوحة القيادة',
    icon: 'icon-bank',
    class: '',
  },
  {
    path: '/users',
    title: 'Users',
    rtlTitle: 'لوحة القيادة',
    icon: 'icon-single-02',
    class: '',
  },
  {
    path: '/accounts',
    title: 'Accounts',
    rtlTitle: 'إخطارات',
    icon: 'icon-chart-bar-32',
    class: '',
  },
  {
    path: '/credit-cards',
    title: 'Credit Cards',
    rtlTitle: 'الرموز',
    icon: 'icon-credit-card',
    class: '',
  },
  {
    path: '/operations',
    title: 'Operations',
    rtlTitle: 'الرموز',
    icon: 'icon-tap-02',
    class: '',
  },
  {
    path: '/transactions',
    title: 'Tranactions',
    rtlTitle: 'خرائط',
    icon: 'icon-single-copy-04',
    class: '',
  }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  isClicked = true;

  constructor(private router: Router) {}

  ngOnInit() {
    this.menuItems = ROUTES.filter((menuItem) => menuItem);
  }

  isMobileMenu() {
    if (window.innerWidth > 991) {
      return false;
    }
    return true;
  }

  navigate(url) {
    this.router.navigate([url]);
  }
}
