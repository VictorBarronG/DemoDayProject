import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Car } from './carClass/car';
import {tap, catchError} from 'rxjs/operators';
import { searchParameters } from './carClass/searchParameters';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  baseURL : string = "http://localhost:8080/api";
  httpOptions = {headers: new HttpHeaders({"Content-Type" : "application/json"})}
  constructor(private http: HttpClient) { 

  }

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

}
