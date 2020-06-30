import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { UserType } from '../../../core/model/userType.models';
import { UserTypeSevice } from '../../../core/auth/_services/userType.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'kt-userType-update',
  templateUrl: './userType-update.component.html',
  styleUrls: ['./userType-update.component.scss']
})
export class UserTypeUpdateComponent implements OnInit {
  formValidation: FormGroup;
  submitControl=false;
  alertType:string;
  alertMessage:string;
  userType:UserType;
  userTypeId:any;
  alertStatus=false;
  constructor(public formBuilder: FormBuilder,private userTypeService:UserTypeSevice,private router:Router,private route:ActivatedRoute) { }

  ngOnInit() {
    this.formValidation = this.formBuilder.group({
    userTypeName: ['', [Validators.required]],
    userTypeDescription: ['', [Validators.required]]
  });
  const id = +this.route.snapshot.paramMap.get('id');
  this.userTypeService.getById(id).subscribe(res => {
    this.userType = res;
    this.userTypeId=this.userType.id;
    this.formValidation.setValue({
      userTypeName: this.userType.name,
      userTypeDescription: this.userType.description,
    });
  });
  }
  submit(formDirective: FormGroupDirective) {
    this.submitControl = true;
    if (this.formValidation.status == "VALID") {
      this.userType = {
        id:this.userTypeId,
        name:this.formValidation.value.userTypeName,
        description:this.formValidation.value.userTypeDescription
      }
      this.userTypeService.save(this.userType).subscribe(res => {
        if (res) {
          this.alertMessage="Başarılı Bir Şekilde Tamamlandı.";
          this.alertType="success";
          this.alertStatus=true;
          formDirective.resetForm();
          setTimeout(() => this.router.navigateByUrl("/userType-list"), 1000);
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
