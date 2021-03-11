import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InventoryService } from '../inventory.service';
import {searchParameters} from '../carclass/searchParameters';
import { Car } from '../carclass/car';
import { SearchParamService } from '../search-param.service';

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
  cars : Car[];

  constructor(private service:InventoryService, private router : Router, private searchService : SearchParamService) {
    this.service.getMakes().subscribe(list => {this.makes = list});
   }

  ngOnInit(): void {
  }

  simpleSearch(){
    let simpleParameter: searchParameters;
    simpleParameter = {make : this.make, model : this.model};
    this.searchService.setSearch(simpleParameter);
    this.router.navigate(["SearchFilter"]);
  }

  getModels(){
    this.service.getModels(this.make).subscribe(list => {this.models = list});
  }

}
