import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from "./app.component";
import { ListingComponent } from "./listing/listing.component";
import {HomeComponent} from "./home/home.component";
import {CheckoutComponent} from "./checkout/checkout.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'item', component: ListingComponent },
  { path: 'checkout', component: CheckoutComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
