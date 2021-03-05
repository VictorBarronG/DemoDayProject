import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Car } from './carClass/car';
import {tap, catchError} from 'rxjs/operators';
import { searchParameters } from './carclass/searchParameters';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  baseURL : string = "http://localhost:8080/api";
  httpOptions = {headers: new HttpHeaders({"Content-Type" : "application/json"})}
  constructor(private http: HttpClient) { 

  }
//gets all available cars in the inventory
  getAllCars(): Observable<Car[]>{
    return this.http.get<Car[]>(this.baseURL + "/allvehicles")
    .pipe(
      tap(x => console.log(x)),
      catchError(err =>{
        console.log(err);
        let empty : Car[] = [];
        return of(empty);
      })
    );
  }

  //allows user to add a vehicle into the inventory
  addCar(toAdd : Car) : Observable<Car> {
    console.log(toAdd);
    return this.http.post<Car>(this.baseURL + "/addcar", toAdd, this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err =>{
        console.log(err);
        return of(null);
      })
    )
  }

  //returns a list of vehicles that match the searchparameters
  searchByFilter(toSearch : searchParameters) : Observable<Car[]> {
    console.log(toSearch);
    return this.http.post<Car[]>(this.baseURL + "/searchbyfilters", toSearch, this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err =>{
        console.log(err);
        return of(null);
      })
    )
  }

  //returns the single car by searching the id
  searchById(Id : number) : Observable<Car> {
    console.log(Id);
    let url = this.baseURL + "/searchbyid/" + Id;
    return this.http.get<Car>(url)
    .pipe(
      tap(x => console.log(x)),
      catchError(err =>{
        console.log(err);
        return of(null);
      })
    )
  }

  //take in a car with an id and allows the user to change all information except the id
  editCar(toEdit : Car) : Observable<Car> {
    console.log(toEdit);
    return this.http.put<Car>(this.baseURL + "/editinventory", toEdit, this.httpOptions)
    .pipe(
      tap(x => console.log(x)),
      catchError(err =>{
        console.log(err);
        return of(null);
      })
    )
  }

  //deletes the single vehicle from the database referenced by the id
  deleteById(Id : number) : Observable<Car> {
    console.log(Id);
    let url = this.baseURL + "/delete/" + Id;
    return this.http.delete(url)
    .pipe(
      tap(x => console.log(x)),
      catchError(err =>{
        console.log(err);
        return of(null);
      })
    )
  }

  getMakes(): Observable<string[]>{
    return this.http.get<string[]>(this.baseURL + "/getMakes")
    .pipe(
      tap(x => console.log(x)),
      catchError(err =>{
        console.log(err);
        let empty : string[] = [];
        return of(empty);
      })
    );
  }

  getModels(make : String): Observable<string[]>{
    return this.http.get<string[]>(this.baseURL + "/getModels/" + make)
    .pipe(
      tap(x => console.log(x)),
      catchError(err =>{
        console.log(err);
        let empty : string[] = [];
        return of(empty);
      })
    );
  }


}
