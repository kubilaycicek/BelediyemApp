import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormGroupDirective } from '@angular/forms';
import { DepartmentService } from '../../../core/auth/_services/department.service';
import { Department } from '../../../core/model/department.models';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'kt-department-update',
  templateUrl: './department-update.component.html',
  styleUrls: ['./department-update.component.scss']
})
export class DepartmentUpdateComponent implements OnInit {

  formValidation: FormGroup;
  submitControl=false;
  alertType:string;
  alertMessage:string;
  department:Department;
  departmentId:any;
  alertStatus=false;
  constructor(
    public formBuilder: FormBuilder,
    private departmentService:DepartmentService,
    private route: ActivatedRoute,
    private router:Router) { }

  ngOnInit() {
    this.formValidation = this.formBuilder.group({
    departmentName: ['', [Validators.required]],
    departmentDescription: ['', [Validators.required]]
  });
  const id = +this.route.snapshot.paramMap.get('id');
  this.departmentService.getById(id).subscribe(res => {
    this.department = res;
    this.departmentId=this.department.id;
    this.formValidation.setValue({
      departmentName: this.department.name,
      departmentDescription: this.department.description,
    });
  });
  }
  submit(formDirective: FormGroupDirective) {
    this.submitControl = true;
    if (this.formValidation.status == "VALID") {
      this.department = {
        id:this.departmentId,
        name:this.formValidation.value.departmentName,
        description:this.formValidation.value.departmentDescription
      }
      this.departmentService.save(this.department).subscribe(res => {
        if (res) {
          this.alertMessage="Başarılı Bir Şekilde Tamamlandı.";
          this.alertType="success";
          this.alertStatus=true;
          formDirective.resetForm();
          setTimeout(() => this.router.navigateByUrl("/department-list"), 1000);
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
