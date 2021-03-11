import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Car } from '../carclass/car';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  @Input() car : Car | undefined;
  price : any | undefined;
  

  constructor(private http: HttpClient, private service: InventoryService, private router : Router, private route: ActivatedRoute) {   }

  ngOnInit(): void {
    this.price =  this.car.price.toLocaleString("en-US");
    
  }

  deleteCar(): void{
    this.service.deleteById(this.car.id).subscribe((_) => {this.router.navigate(["fullinventory"])});
    alert("Deleted");
    window.location.reload();
  }

  editCar() : void{
    this.router.navigate(['editcar', {id : this.car.id}]);
  }

  viewCar() : void{
    this.router.navigate(['viewcar', {id : this.car.id}]);
  }


}
