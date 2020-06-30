import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { Category } from '../../../core/model/category.models';
import { CategoryService } from '../../../core/auth/_services/category.service';
import { Department } from '../../../core/model/department.models';
import { DepartmentService } from '../../../core/auth/_services/department.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'kt-category-update',
  templateUrl: './category-update.component.html',
  styleUrls: ['./category-update.component.scss']
})
export class CategoryUpdateComponent implements OnInit {

  formValidation: FormGroup;
  submitControl = false;
  alertType: string;
  alertMessage: string;
  category: Category;
  categoryId:any;
  departments: Department[];
  alertStatus = false;
  constructor(
    public formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private departmentService: DepartmentService,
    private route: ActivatedRoute,
    private router:Router) { }

  ngOnInit() {
    this.formValidation = this.formBuilder.group({
      categoryName: ['', [Validators.required]],
      categoryDescription: ['', [Validators.required]],
      departmentSelect: ['', [Validators.required]]
    });
    this.departmentService.getAll().subscribe(res => {
      this.departments = res;
    });
    const id = +this.route.snapshot.paramMap.get('id');
    this.categoryService.getById(id).subscribe(res => {
      this.category = res;
      this.categoryId=this.category.id;
      this.formValidation.setValue({
        categoryName: this.category.name,
        categoryDescription: this.category.description,
        departmentSelect: this.category.departmentDto.id,
      });
    });
  }

  submit(formDirective: FormGroupDirective) {
    this.submitControl = true;
    if (this.formValidation.status == "VALID") {
      this.category = {
        id:this.categoryId,
        name: this.formValidation.value.categoryName,
        description: this.formValidation.value.categoryDescription,
        departmentDto: this.departments.find(department => department.id==this.formValidation.value.departmentSelect)
      }
      this.categoryService.update(this.category).subscribe(res => {
        if (res) {
          this.alertMessage = "Başarılı Bir Şekilde Tamamlandı."
          this.alertType = "success";
          this.alertStatus = true;
          formDirective.resetForm();
          setTimeout(() => this.router.navigateByUrl("/category-list"), 1000);
          setTimeout(() => this.submitControl = false, 1000);
          setTimeout(() => this.alertStatus = false, 1000);
        }
        else {
          this.alertStatus = true;
          this.alertMessage = "Kaydedilirken Hata Oluştu."
          this.alertType = "danger";
        }
      },
        err => {
          if (err) {
            this.alertStatus = true;
            this.alertMessage = "Kaydedilirken Hata Oluştu."
            this.alertType = "danger";
          }
        });
    }
  }

}
