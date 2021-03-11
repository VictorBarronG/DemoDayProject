import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  user : string;
  password : string;
  isLoggedIn : boolean;

  constructor() { }

  setUser(user : string){
    this.user = user;
    this.isLoggedIn = true;
  }

  getStatus() : boolean{
    return this.isLoggedIn;
  }

}
