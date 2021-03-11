import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Car } from '../carclass/car';
import { searchParameters } from '../carclass/searchParameters';
import { InventoryService } from '../inventory.service';
import { SearchParamService } from '../search-param.service';

@Component({
  selector: 'app-search-filter',
  templateUrl: './search-filter.component.html',
  styleUrls: ['./search-filter.component.css']
})
export class SearchFilterComponent implements OnInit {
  make : string = "";
  model : string;
  miles : number;
  color : string;
  yearStart : number;
  yearEnd:number;
  owners : number;
  passinspec : boolean = true;
  priceStart : number;
  priceEnd: number;

  makes : string[];
  models: string[];
  cars:Car[];
  firstPageLoad: boolean = true;

  constructor(private service : InventoryService, private router : Router, private searchPara : SearchParamService) { 
   
  }

  ngOnInit(): void {
    this.service.getMakes().subscribe(list => {this.makes = list;
      let params : searchParameters = this.searchPara.getSearch();
      console.log(params)
      if(params){
        this.make = params.make;
        this.model = params.model;
        this.searchPara.clearSearchPara();
        this.searchFilter();
      }
    });
  }


  getModels(){
    this.service.getModels(this.make).subscribe(list => {this.models = list});
  }

  searchFilter(){
      let toSearch : searchParameters = {make : this.make, model : this.model, 
                        miles : this.miles, color : this.color,
                        yearStart : this.yearStart, yearEnd: this.yearEnd,
                        owners : this.owners,
                        passedInspec : this.passinspec,
                        priceStart : this.priceStart, priceEnd:this.priceEnd};
      this.service.searchByFilter(toSearch).subscribe(list => {this.cars = list});
    }

}
