import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'congesAngular';

  public constructor(private router: Router) {}

  public get login(): string {
    return localStorage.getItem('login');
  }

  public logout() {
    localStorage.clear();
    this.router.navigate(['/home']);
  }
}
