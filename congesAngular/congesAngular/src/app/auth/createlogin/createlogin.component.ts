import { Router } from '@angular/router';
import { InscriptionService } from './../../services/inscription.service';
import { Component, OnInit } from '@angular/core';
import { Login } from 'src/app/model/login';
import { AbstractControl, AsyncValidatorFn, Form, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { debounceTime, map } from 'rxjs/operators';

@Component({
  selector: 'app-createlogin',
  templateUrl: './createlogin.component.html',
  styleUrls: ['./createlogin.component.css']
})
export class CreateloginComponent implements OnInit {
  loginCtrl: FormControl;
  passwordCtrl:  FormControl;
  confirmationCtrl : FormControl;
  passwordGroup : FormGroup;
  form : FormGroup;

  constructor(private InscriptionService: InscriptionService, private route: Router, private fb:FormBuilder) {
    this.loginCtrl = this.fb.control(
      '',
      [Validators.required, Validators.pattern(/^.{3}.*@.+\.\w{2,3}$/)],
      this.controlLogin(),
    );
    this.passwordCtrl = this.fb.control(
      '',
      [Validators.required, Validators.minLength(6)],

    );
    this.confirmationCtrl = this.fb.control('', Validators.required);
    this.passwordGroup = this.fb.group(
      {
        password : this.passwordCtrl,
        confirmation : this.confirmationCtrl
      },
      {
        validators: this.checkPassword,
      }
    );
    this.form = this.fb.group({
      login: this.loginCtrl,
      passwordGroup: this.passwordGroup,
    });
  }

  ngOnInit(): void {
  }

  controlLogin(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return this.InscriptionService.checkLogin(control.value).pipe(
        debounceTime(500),
        map((result: boolean) => {
          return result ? { loginExist: true } : null;
        })
      );
    };
  }

  checkPassword(group: FormGroup){
    const password = group.controls['password'];
    const confirmation = group.controls.confirmation;
    if (password.errors) {
      return null;
    }
    return password.value != confirmation.value ? { notEquals: true } : null;
  }

  send(){
    this.InscriptionService.inscription(this.loginCtrl.value, this.passwordCtrl.value).subscribe((res) => {
      this.route.navigate(['/login']);
    })
  }

  loginIsInvalid(): boolean {
    return this.loginCtrl.dirty && this.loginCtrl.invalid;
  }

  passwordIsInvalid(): boolean {
    return this.passwordCtrl.dirty && this.passwordCtrl.invalid;
  }

  passwordGroupIsInvalid() {
    return this.passwordGroup.dirty && this.passwordGroup.invalid;
  }
}
