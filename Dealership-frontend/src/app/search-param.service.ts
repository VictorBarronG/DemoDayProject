import { Injectable } from '@angular/core';
import { searchParameters } from './carclass/searchParameters';

@Injectable({
  providedIn: 'root'
})
export class SearchParamService {
  searchPara : searchParameters | undefined;

  constructor() { }

  getSearch() : searchParameters {
    return this.searchPara;
  }

  setSearch(searchPara : searchParameters) : void{
    this.searchPara = searchPara;
  }

  clearSearchPara() : void {
    this.searchPara = undefined;
  }
}
