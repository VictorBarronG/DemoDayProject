import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Car } from '../carclass/car';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  @Input() car : Car | undefined;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }


}
