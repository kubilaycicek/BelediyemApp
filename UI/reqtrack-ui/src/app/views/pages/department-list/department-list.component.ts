import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { Department } from '../../../core/model/department.models';
import { DepartmentService } from '../../../core/auth/_services/department.service';

@Component({
  selector: 'kt-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.scss']
})
export class DepartmentListComponent implements OnInit {
  displayedColumns = ['id', 'name', 'description','operation'];
  dataSource: MatTableDataSource<Department>;
  department:Department[]=[];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private departmentService:DepartmentService) {
      this.dataSource = new MatTableDataSource(this.department);
  }
 
ngOnInit(): void {
  this.paginator._intl.itemsPerPageLabel="Sayfa Başı Gösterim: ";
  this.paginator._intl.firstPageLabel="İlk";
  this.paginator._intl.lastPageLabel="Son";
  this.paginator._intl.nextPageLabel="Sonraki";
  this.paginator._intl.previousPageLabel="Önceki";
  this.paginator.showFirstLastButtons=true;
    this.departmentService.getAll().subscribe(data => {
      this.department = data;
      this.dataSource = new MatTableDataSource(this.department);
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
}

