import { NgModule } from "@angular/core";
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { MatFormField, MatLabel } from "@angular/material/form-field";

const ANGULAR_MATERIAL = [
  MatButtonModule,
  MatInputModule,
  MatIconModule,
  MatCardModule,
  MatFormField,
  MatLabel
];

@NgModule({
  declarations: [],
  imports:[...ANGULAR_MATERIAL],
  exports:[
    ...ANGULAR_MATERIAL,
  ],
})

export class SharedModule {
}
