import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  //all paths should have app/ prefix 
  //it will usually be { path: 'app/sth', component: SomeClassComponent }
  { path: 'app', children: [] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
