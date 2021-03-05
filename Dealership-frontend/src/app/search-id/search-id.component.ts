import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Car } from '../carclass/car';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-search-id',
  templateUrl: './search-id.component.html',
  styleUrls: ['./search-id.component.css']
})
export class SearchIdComponent implements OnInit {

  id : number;
  cars : Car[];

  constructor(private service : InventoryService, private router : Router) { }

  ngOnInit(): void {
  }
  
  searchId(){
    this.service.searchById(this.id).subscribe(x => {this.cars = [x]});
  }

}
