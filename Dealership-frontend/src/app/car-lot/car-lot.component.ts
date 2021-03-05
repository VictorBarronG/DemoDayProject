import { Component, OnInit } from '@angular/core';
import { Car } from '../carclass/car';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-car-lot',
  templateUrl: './car-lot.component.html',
  styleUrls: ['./car-lot.component.css']
})
export class CarLotComponent implements OnInit {
  cars: Car[];

  constructor(private invenService : InventoryService) { }

  ngOnInit(): void {
    this.invenService.getAllCars().subscribe(list =>{
      this.cars = list;
    });
  }

}
