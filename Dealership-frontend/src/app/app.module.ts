import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { AddCarComponent } from './add-car/add-car.component';
import { CarComponent } from './car/car.component';
import { CarLotComponent } from './car-lot/car-lot.component';
import { SearchFilterComponent } from './search-filter/search-filter.component';
import { SearchIdComponent } from './search-id/search-id.component';
import { EditCarComponent } from './edit-car/edit-car.component';
import { DeleteCarComponent } from './delete-car/delete-car.component';
import { HomeSimpleSearchComponent } from './home-simple-search/home-simple-search.component';
@NgModule({
  declarations: [
    AppComponent,
    AddCarComponent,
    CarComponent,
    CarLotComponent,
    SearchFilterComponent,
    SearchIdComponent,
    EditCarComponent,
    DeleteCarComponent,
    HomeSimpleSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
