import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
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
  description : string;
  car : Car;

  constructor(private service:InventoryService, private router : Router, private route: ActivatedRoute ) { 
    

  }

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.service.searchById(this.id).subscribe(returnedCar => {this.car = returnedCar});
  }

  editCar(){
    this.service.editCar(this.car).subscribe(returnedCar => {this.router.navigate(['viewcar', {id : this.car.id}])});
  }

}
