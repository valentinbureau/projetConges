import { NgModule } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { FormsModule } from '@angular/forms';

const ANGULAR_MATERIAL = [
  MatButtonModule,
  MatInputModule,
  MatIconModule,
  MatCardModule,
];

@NgModule({
  declarations: [],
  imports: [...ANGULAR_MATERIAL,FormsModule],
  exports: [FormsModule,...ANGULAR_MATERIAL]
})


export class SharedModule {
}
