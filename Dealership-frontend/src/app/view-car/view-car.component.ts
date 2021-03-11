import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Car } from '../carclass/car';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-view-car',
  templateUrl: './view-car.component.html',
  styleUrls: ['./view-car.component.css']
})
export class ViewCarComponent implements OnInit {

  id: number;
  car : Car;
  price : any | undefined;


  constructor(private service:InventoryService, private router : Router, private route: ActivatedRoute) {
    
   }

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.service.searchById(this.id).subscribe(returnedCar => {this.car = returnedCar});
  }

}
