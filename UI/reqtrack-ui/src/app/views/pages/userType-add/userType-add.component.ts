import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { UserType } from '../../../core/model/userType.models';
import { UserTypeSevice } from '../../../core/auth/_services/userType.service';

@Component({
  selector: 'kt-userType-add',
  templateUrl: './userType-add.component.html',
  styleUrls: ['./userType-add.component.scss']
})
export class UserTypeAddComponent implements OnInit {
  formValidation: FormGroup;
  submitControl=false;
  alertType:string;
  alertMessage:string;
  userType:UserType;
  alertStatus=false;
  constructor(public formBuilder: FormBuilder,private userTypeService:UserTypeSevice) { }

  ngOnInit() {
    this.formValidation = this.formBuilder.group({
    userTypeName: ['', [Validators.required]],
    userTypeDescription: ['', [Validators.required]]
  });
  }
  submit(formDirective: FormGroupDirective) {
    this.submitControl = true;
    if (this.formValidation.status == "VALID") {
      this.userType = {
        name:this.formValidation.value.userTypeName,
        description:this.formValidation.value.userTypeDescription
      }
      this.userTypeService.save(this.userType).subscribe(res => {
        if (res) {
          this.alertMessage="Başarılı Bir Şekilde Tamamlandı.";
          this.alertType="success";
          this.alertStatus=true;
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
