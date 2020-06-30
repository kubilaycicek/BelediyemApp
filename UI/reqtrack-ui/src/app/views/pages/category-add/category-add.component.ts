import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { Category } from '../../../core/model/category.models';
import { CategoryService } from '../../../core/auth/_services/category.service';
import { Department } from '../../../core/model/department.models';
import { DepartmentService } from '../../../core/auth/_services/department.service';

@Component({
  selector: 'kt-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.scss']
})
export class CategoryAddComponent implements OnInit {

  formValidation: FormGroup;
  submitControl=false;
  alertType:string;
  alertMessage:string;
  category:Category;
  departments:Department[]=[];
  alertStatus=false;
  constructor(public formBuilder: FormBuilder,private categoryService:CategoryService,private departmentService:DepartmentService) { }

  ngOnInit() {
    this.formValidation = this.formBuilder.group({
    categoryName: ['', [Validators.required]],
    categoryDescription: ['', [Validators.required]],
    departmentSelect:['', [Validators.required]]
  });
  this.departmentService.getAll().subscribe(res => {
    this.departments=res;
  });
  }
  submit(formDirective: FormGroupDirective) {
    this.submitControl = true;
    if (this.formValidation.status == "VALID") {
      this.category = {
        name:this.formValidation.value.categoryName,
        description:this.formValidation.value.categoryDescription,
        departmentDto: this.formValidation.value.departmentSelect
      }
      console.log(this.category);
      
      this.categoryService.save(this.category).subscribe(res => {
        if (res) {
          this.alertMessage="Başarılı Bir Şekilde Tamamlandı.";
          this.alertType="success";
          this.alertStatus=true;
          formDirective.resetForm();
          setTimeout(()=>this.submitControl = false, 1000);
          setTimeout(()=>this.alertStatus = false, 1000);
        }
        else {
          this.alertStatus=true;
          this.alertMessage="Kaydedilirken Hata Oluştu.";
          this.alertType="danger";
        }
      },
      err => {
        if(err){
          this.alertStatus=true;
          this.alertMessage="Kaydedilirken Hata Oluştu."
          this.alertType="danger";
        }
      });
    }
  }

}
