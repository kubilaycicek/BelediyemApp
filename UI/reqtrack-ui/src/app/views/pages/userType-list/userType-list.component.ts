import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { Category } from '../../../core/model/category.models';
import { UserTypeSevice } from '../../../core/auth/_services/userType.service';
import { UserType } from '../../../core/model/userType.models';

@Component({
  selector: 'kt-userType-list',
  templateUrl: './userType-list.component.html',
  styleUrls: ['./userType-list.component.scss']
})
export class UserTypeListComponent implements OnInit {
  displayedColumns = ['id', 'name', 'description'];
  dataSource: MatTableDataSource<Category>;
  userType:UserType[] = [];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private userTypeService:UserTypeSevice, private changeDetectorRefs:ChangeDetectorRef) {
      this.dataSource = new MatTableDataSource(this.userType);
  }
  ngOnInit(): void {
    this.paginator._intl.itemsPerPageLabel="Sayfa Başı Gösterim: ";
    this.paginator._intl.firstPageLabel="İlk";
    this.paginator._intl.lastPageLabel="Son";
    this.paginator._intl.nextPageLabel="Sonraki";
    this.paginator._intl.previousPageLabel="Önceki";
    this.paginator.showFirstLastButtons=true;
    this.userTypeService.getAll().subscribe(data => {
      this.userType = data;
      this.dataSource = new MatTableDataSource(this.userType);
      this.changeDetectorRefs.detectChanges();
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  /**
  * Set the paginator and sort after the view init since this component will
  * be able to query its view for the initialized paginator and sort.
  */
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
  getStatusType(status: string){
    if(status == "Aktif"){
      return "success";
    }
    else if(status == "Pasif"){
      return "danger"
    }
    else{
      return "metal"
    }
  }
  getColor(id: any){
    if(id%2 == 0){
      return "white";
    }
    else {
      return "light"
    }
  }
}






