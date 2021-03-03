import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCarComponent } from './add-car/add-car.component';
import { CarLotComponent } from './car-lot/car-lot.component';
import { DeleteCarComponent } from './delete-car/delete-car.component';
import { EditCarComponent } from './edit-car/edit-car.component';
import { SearchFilterComponent } from './search-filter/search-filter.component';
import { SearchIdComponent } from './search-id/search-id.component';

const routes: Routes = [{path:"", component: CarLotComponent},
                        {path:"add", component: AddCarComponent},
                        {path:"SearchFilter", component: SearchFilterComponent},
                        {path:"Searchid", component:SearchIdComponent},
                        {path:"editcar", component:EditCarComponent},
                        {path:"delete", component: DeleteCarComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
