import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { Users } from '../../../core/model/user.models';
import { UserService } from '../../../core/auth/_services/user.service';

/**
* @title Data table with sorting, pagination, and filtering.
*/
@Component({
  selector: 'kt-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent {
  displayedColumns = ['id','tcnumber','name', 'surname','phone','email','operation'];
  dataSource: MatTableDataSource<Users>;
  user:Users[]=[];
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private userService:UserService,private changeDetectorRefs: ChangeDetectorRef) {

      // Assign the data to the data source for the table to render
      this.dataSource = new MatTableDataSource(this.user);
  }
  ngOnInit(): void {
    this.paginator._intl.itemsPerPageLabel="Sayfa Başı Gösterim: ";
    this.paginator._intl.firstPageLabel="İlk";
    this.paginator._intl.lastPageLabel="Son";
    this.paginator._intl.nextPageLabel="Sonraki";
    this.paginator._intl.previousPageLabel="Önceki";
    this.paginator.showFirstLastButtons=true;
    this.userService.getAll().subscribe((data => {
      this.user = data;
      this.dataSource = new MatTableDataSource(this.user);
      this.changeDetectorRefs.detectChanges();
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }));
    
  }
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





