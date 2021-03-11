import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { InventoryService } from '../inventory.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  user : string;
  password : string;
  @Output() logged : EventEmitter<any> = new EventEmitter();

  constructor(private loginServ : LoginService, private router : Router, private log : LoginService) { }

  ngOnInit(): void {
  }

  login() : void{
    this.log.setUser(this.user);
    this.router.navigate([""]);
    this.logged.emit(true);
  
  }

}
