import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormGroupDirective } from '@angular/forms';
import { DepartmentService } from '../../../core/auth/_services/department.service';
import { Department } from '../../../core/model/department.models';

@Component({
  selector: 'kt-department-add',
  templateUrl: './department-add.component.html',
  styleUrls: ['./department-add.component.scss']
})
export class DepartmentAddComponent implements OnInit {

  formValidation: FormGroup;
  submitControl=false;
  alertType:string;
  alertMessage:string;
  department:Department;
  alertStatus=false;
  constructor(public formBuilder: FormBuilder,private departmentService:DepartmentService) { }

  ngOnInit() {
    this.formValidation = this.formBuilder.group({
    departmentName: ['', [Validators.required]],
    departmentDescription: ['', [Validators.required]]
  });
  }
  submit(formDirective: FormGroupDirective) {
    this.submitControl = true;
    if (this.formValidation.status == "VALID") {
      this.department = {
        name:this.formValidation.value.departmentName,
        description:this.formValidation.value.departmentDescription
      }
      this.departmentService.save(this.department).subscribe(res => {
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
