import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { UserService } from '../../../core/auth/_services/user.service';
import { Users } from '../../../core/model/user.models';
import { UserTypeSevice } from '../../../core/auth/_services/userType.service';
import { UserType } from '../../../core/model/userType.models';

@Component({
  selector: 'kt-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.scss']
})
export class UserAddComponent implements OnInit {

  formValidation: FormGroup;
  submitControl=false;
  alertType:string;
  alertMessage:string;
  userTypes:UserType[]=[];
  user:Users;
  alertStatus=false;
  constructor(public formBuilder: FormBuilder,private userService:UserService,private userTypeService:UserTypeSevice) { }

  ngOnInit() {
    this.formValidation = this.formBuilder.group({
    userName: ['', [Validators.required]],
    userSurname: ['', [Validators.required]],
    userTcNumber: ['', [Validators.required]],
    userPhone: ['', [Validators.required]],
    userAddress: ['', [Validators.required]],
    userPassword: ['', [Validators.required]],
    userEmail: ['', [Validators.required]],
    userUserTypeSelect: ['', [Validators.required]]
  });
  this.userTypeService.getAll().subscribe(res => {
    this.userTypes=res;
  });
  }
  submit(formDirective: FormGroupDirective) {
    this.submitControl = true;
    if (this.formValidation.status == "VALID") {
      this.user = {
        name:this.formValidation.value.userName,
        surname:this.formValidation.value.userSurname,
        tcNumber:this.formValidation.value.userTcNumber,
        phone:this.formValidation.value.userPhone,
        password:this.formValidation.value.userPassword,
        address:this.formValidation.value.userAddress,
        email:this.formValidation.value.userEmail,
        userTypeDto:this.formValidation.value.userUserTypeSelect
      }
      this.userService.save(this.user).subscribe(res => {
        if (res) {
          this.alertStatus=true;
          this.alertMessage="Başarılı Bir Şekilde Tamamlandı.";
          this.alertType="success";
          formDirective.resetForm();
          setTimeout(()=>this.submitControl = false, 1000);
          setTimeout(()=>this.alertStatus = false, 1000);
        }
        else {
          this.alertMessage="Kaydedilirken Hata Oluştu.";
          this.alertType="danger";
        }
      });
    }
  }
}

