import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-delete-car',
  templateUrl: './delete-car.component.html',
  styleUrls: ['./delete-car.component.css']
})
export class DeleteCarComponent implements OnInit {

  id : number;

  constructor(private service:InventoryService, private router : Router) { }

  ngOnInit(): void {
  }

  deleteCar(){
    this.service.deleteById(this.id).subscribe((_) => {this.router.navigate([""])})
  }

}
