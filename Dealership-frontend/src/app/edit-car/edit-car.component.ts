import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Car } from '../carclass/car';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-edit-car',
  templateUrl: './edit-car.component.html',
  styleUrls: ['./edit-car.component.css']
})
export class EditCarComponent implements OnInit {

  id : number;
  make : string;
  model : string;
  miles : number;
  color : string;
  year : number;
  owners : number;
  passinspec : boolean = false;
  vin : string;
  price : number;

  constructor(private service:InventoryService, private router : Router ) { }

  ngOnInit(): void {
  }

  editCar(){
    let toEdit : Car = {
      id : this.id,
      make : this.make, model : this.model, 
      miles : this.miles, color : this.color,
      year : this.year, owners : this.owners,
      passedInspec : this.passinspec, vin : this.vin,
      price : this.price
    };
    this.service.editCar(toEdit).subscribe((_) => {this.router.navigate([""])})
  }

}
