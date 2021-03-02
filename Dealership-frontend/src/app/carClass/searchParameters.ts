export interface searchParameters {
    make? : string;
    model? : string;
    miles? : number;
    color? : string;
    yearStart? : number;
    yearEnd? : number;
    owners? : number;
    passedInspec? : boolean;
    priceStart? : number;
    priceEnd?: number;
}