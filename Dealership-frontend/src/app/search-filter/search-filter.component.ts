import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Car } from '../carclass/car';
import { searchParameters } from '../carclass/searchParameters';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-search-filter',
  templateUrl: './search-filter.component.html',
  styleUrls: ['./search-filter.component.css']
})
export class SearchFilterComponent implements OnInit {
  make : string = null;
  model : string;
  miles : number;
  color : string;
  yearStart : number;
  yearEnd:number;
  owners : number;
  passinspec : boolean = false;
  priceStart : number;
  priceEnd: number;

  cars:Car[];

  constructor(private service : InventoryService, private router : Router) { }

  ngOnInit(): void {
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
