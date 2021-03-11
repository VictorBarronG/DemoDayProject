import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCarComponent } from './add-car/add-car.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { CarLotComponent } from './car-lot/car-lot.component';
import { DeleteCarComponent } from './delete-car/delete-car.component';
import { EditCarComponent } from './edit-car/edit-car.component';
import { HomeSimpleSearchComponent } from './home-simple-search/home-simple-search.component';
import { SearchFilterComponent } from './search-filter/search-filter.component';
import { SearchIdComponent } from './search-id/search-id.component';
import { ViewCarComponent } from './view-car/view-car.component';

const routes: Routes = [{path:"", component: HomeSimpleSearchComponent},
                        {path:"add", component: AddCarComponent},
                        {path:"SearchFilter", component: SearchFilterComponent},
                        {path:"Searchid", component:SearchIdComponent},
                        {path:"editcar", component:EditCarComponent},
                        {path:"delete", component: DeleteCarComponent},
                        {path:"fullinventory", component:CarLotComponent},
                        {path: "viewcar", component:ViewCarComponent},
                        {path: "login", component:AdminLoginComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
