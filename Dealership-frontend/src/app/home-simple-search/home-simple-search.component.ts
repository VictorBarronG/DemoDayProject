import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InventoryService } from '../inventory.service';
import {searchParameters} from '../carclass/searchParameters';

@Component({
  selector: 'app-home-simple-search',
  templateUrl: './home-simple-search.component.html',
  styleUrls: ['./home-simple-search.component.css']
})
export class HomeSimpleSearchComponent implements OnInit {

  makes : string[];
  models: string[];

  make : string;
  model : string;

  constructor(private service:InventoryService, private router : Router) {
    this.service.getMakes().subscribe(list => {this.makes = list});
   }

  ngOnInit(): void {
  }

  simpleSearch(){
    let simpleParameter: searchParameters;
    simpleParameter = {make : this.make, model : this.model};
    this.service.searchByFilter(simpleParameter).subscribe();
  }

  getModels(){
    this.service.getModels(this.make).subscribe(list => {this.models = list});
  }

}
