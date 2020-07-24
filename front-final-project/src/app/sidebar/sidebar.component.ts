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
    path: '/contacts',
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
    path: '/leads',
    title: 'Operations',
    rtlTitle: 'الرموز',
    icon: 'icon-atom',
    class: '',
  },
  {
    path: '/opportunities',
    title: 'Tranactions',
    rtlTitle: 'خرائط',
    icon: 'icon-spaceship',
    class: '',
  },
  {
    path: '/contacts',
    title: 'Contacts',
    rtlTitle: 'خرائط',
    icon: 'icon-single-02',
    class: '',
  },
  {
    path: '/accounts',
    title: 'Accounts',
    rtlTitle: 'خرائط',
    icon: 'icon-bank',
    class: '',
  },
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
