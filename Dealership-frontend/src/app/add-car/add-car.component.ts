import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'
import { Car } from '../carclass/car';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.css']
})
export class AddCarComponent implements OnInit {
  make : string;
  model : string;
  miles : number;
  color : string;
  year : number;
  owners : number;
  passinspec : boolean = false;
  vin : string;
  price : number;
  description : string;
  imagePath : string;

  constructor(private service : InventoryService, private router : Router) { }

  ngOnInit(): void {
  }

  //still need function to upload and store image
  //once image is uploaded assign the path to string value imagePath
  uploadImage(imageInput : any){

  }

  addCar(){
    let toAdd : Car = {make : this.make, model : this.model, 
                      miles : this.miles, color : this.color,
                      year : this.year, owners : this.owners,
                      passedInspec : this.passinspec, vin : this.vin,
                      price : this.price, description : this.description,
                      imagePath : this.imagePath};
    this.service.addCar(toAdd).subscribe((_)=> {this.router.navigate([""])});
  }


    

}
