import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title : string = 'Dealership-frontend';
  loggedIn : boolean;

  constructor( private router : Router, private log : LoginService){
    
  }
  goHome() : void{
    this.router.navigate([""]);
  }

  goInventory() : void{
    this.router.navigate(["fullinventory"]);
  }

  goSearchPara() : void{
    this.router.navigate(["SearchFilter"]);
  }

  goToAdd() : void{
    this.router.navigate(["add"]);
  }
  
  goToLogin() : void{
    this.router.navigate(["login"]);
  }

  getUserLoggedIn(event : Event) : void{
    console.log(event);
    this.loggedIn = this.log.getStatus();
  }

  
}



