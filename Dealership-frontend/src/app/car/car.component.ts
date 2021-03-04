import { Component, Input, OnInit } from '@angular/core';
import { Car } from '../carClass/car';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  @Input() car : Car | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
