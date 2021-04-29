import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import {MatSelectModule} from '@angular/material/select';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatMenuModule} from '@angular/material/menu';

const ANGULAR_MATERIAL = [
  MatButtonModule,
  MatInputModule,
  MatIconModule,
  MatCardModule,
  MatNativeDateModule,
  MatDatepickerModule,
  MatAutocompleteModule,
  MatSelectModule,
  MatCheckboxModule,
  MatMenuModule,
];

@NgModule({
  declarations: [],
  imports: [...ANGULAR_MATERIAL, FormsModule],
  exports: [FormsModule, ...ANGULAR_MATERIAL],
})
export class SharedModule {}
