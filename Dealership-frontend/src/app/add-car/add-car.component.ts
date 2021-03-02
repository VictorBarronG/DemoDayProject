import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'
import { Car } from '../carClass/car';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.css']
})
export class AddCarComponent implements OnInit {
  make : string;
  model : string;
  miles : number;
  color : string;
  year : number;
  owners : number;
  passinspec : boolean = false;
  vin : string;
  price : number;

  constructor(private service : InventoryService, private router : Router) { }

  ngOnInit(): void {
  }
  addCar(){
    let toAdd : Car = {make : this.make, model : this.model, 
                      miles : this.miles, color : this.color,
                      year : this.year, owners : this.owners,
                      passedInspec : this.passinspec, vin : this.vin,
                    price : this.price};
    this.service.addCar(toAdd).subscribe((_)=> {this.router.navigate([""])});
  }

}
