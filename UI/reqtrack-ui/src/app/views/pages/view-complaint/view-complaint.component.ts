import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { Lightbox } from 'ngx-lightbox';
import { ActivatedRoute, Router } from '@angular/router';
import { ComplaintService } from '../../../core/auth/_services/complaint.service';
import { ComplaintGalleries } from '../../../core/model/complaint-galleries.models';
import { DomSanitizer } from '@angular/platform-browser';
import { Complaint } from '../../../core/model/complaint.models';
import { ComplaintStatus } from '../../../core/model/complaint-status.models';
import { ComplaintStatusService } from '../../../core/auth/_services/complaint-status.service';
import { ComplaintUpdateModel } from '../../../core/model/complaintUpdate.model';
import { CategoryService } from '../../../core/auth/_services/category.service';
import { Category } from '../../../core/model/category.models';

@Component({
  selector: 'kt-view-complaint',
  templateUrl: './view-complaint.component.html',
  styleUrls: ['./view-complaint.component.scss']
})
export class ViewComplaintComponent implements OnInit {
  private _album: Array<any> = [];
  complaint: Complaint;
  lan:any;
  len:any;
  categories:Category[];
  formValidation: FormGroup;
  submitControl=false;
  alertType:string;
  alertMessage:string;
  complaintStatuses:ComplaintStatus[];
  userTypeStatusId=1;
  complaintUpdate:ComplaintUpdateModel;
  alertStatus=false;
  complaintId:any;
  constructor(
    private _lightbox: Lightbox,
    public formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private complaintService: ComplaintService,
    private categoryService: CategoryService,
    private complaintStatusSevice:ComplaintStatusService,
    private _sanitizer: DomSanitizer,
    private router:Router) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id'); 
    this.formValidation = this.formBuilder.group({
      complaintUserName: ['', [Validators.required]],
      complaintDescription: ['', [Validators.required]],
      statusSelect:['', [Validators.required]],
      categorySelect:['', [Validators.required]]
    });
    this.complaintStatusSevice.getAll().subscribe(res => {
      this.complaintStatuses=res;
    });
    this.categoryService.getAll().subscribe(res=> {
      this.categories=res;
    });
    this.complaintService.getById(id).subscribe(res => {
      console.log(res);
      
      this.setImage(res.complaintGalleries);
      this.complaint=res;
      this.complaintId=res.id;
      var location=res.location.split(",");
      this.lan=location[0];
      this.len=location[1];
      //this.userTypeStatusId=this.complaint.user.userTypeDto.id;
      this.formValidation.setValue({
        complaintUserName: this.complaint.userDto.name + " " +this.complaint.userDto.surname,
        complaintDescription: this.complaint.description,
        statusSelect: this.complaint.complaintStatusDto.id,
        categorySelect:this.complaint.categoryDto.id
      });
    });
  }

  open(index: number): void {
    // open lightbox
    this._lightbox.open(this._album, index);
  }

  setImage(complainGalery: ComplaintGalleries[]) {
    for (let i = 0; i < complainGalery.length; i++) {
      const src = this._sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + complainGalery[i].imageUrl);
      const caption = 'Fotoğraf ' + (i+1).toString();
      const thumb = this._sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' + complainGalery[i].imageUrl);
      const album = {
        src: src,
        caption: caption,
        thumb: thumb
      };

      this._album.push(album);
    }
  }

  close(): void {
    // close lightbox programmatically
    this._lightbox.close();
  }
  
  submit(formDirective: FormGroupDirective) {
    this.submitControl = true;
    if (this.formValidation.status == "VALID") {
      this.complaintUpdate={
        complaintId:this.complaint.id,
        categoryId:this.formValidation.value.categorySelect,
        statusId:this.formValidation.value.statusSelect
      }
      console.log(this.complaintUpdate);
      
      this.complaintService.update(this.complaintUpdate).subscribe(res => {
        if (res) {
          this.alertStatus=true;
          this.alertMessage="Başarılı Bir Şekilde Tamamlandı.";
          this.alertType="success";
          setTimeout(() => this.router.navigateByUrl("/complaint-list"), 1000);
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


