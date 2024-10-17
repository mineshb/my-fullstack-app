import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

/*
@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
  */

export interface User {
  id?: number;
  userName: string;
  email: string;
  firstName: string;
  lastName: string;
}
