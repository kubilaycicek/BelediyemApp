import { Component, OnInit, Input, ViewChild, ChangeDetectorRef } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Complaint } from '../../../core/model/complaint.models';
import { ComplaintService } from '../../../core/auth/_services/complaint.service';
import { DepartmentService } from '../../../core/auth/_services/department.service';
import { Department } from '../../../core/model/department.models';

@Component({
  selector: 'kt-complaint-list',
  templateUrl: './complaint-list.component.html',
  styleUrls: ['./complaint-list.component.scss']
})
export class ComplaintListComponent implements OnInit {
  departments: Department[];
  displayedColumns = ['id', 'description', 'user', 'category', 'status', 'operation'];
  dataSource: MatTableDataSource<Complaint>;
  complaint: Complaint[] = [];
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  selected: any;

  constructor(
    private complaintService: ComplaintService,
    private changeDetectorRefs: ChangeDetectorRef,
    private departmentService: DepartmentService) {
    // Assign the data to the data source for the table to render
    this.dataSource = new MatTableDataSource(this.complaint);
  }

  ngOnInit(): void {
    this.departmentService.getAll().subscribe(res => {
      this.departments = res;
    });
    this.paginator._intl.itemsPerPageLabel = "Sayfa Başı Gösterim: ";
    this.paginator._intl.firstPageLabel = "İlk";
    this.paginator._intl.lastPageLabel = "Son";
    this.paginator._intl.nextPageLabel = "Sonraki";
    this.paginator._intl.previousPageLabel = "Önceki";
    this.paginator.showFirstLastButtons = true;
    this.complaintService.getAll().subscribe((data => {
      this.complaint = data;
      this.dataSource = new MatTableDataSource(this.complaint);
      this.changeDetectorRefs.detectChanges();
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }));
  }

  /**
  * Set the paginator and sort after the view init since this component will
  * be able to query its view for the initialized paginator and sort.
  */
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  selectChange() {
    if (this.selected == 0) {
      this.complaintService.getAll().subscribe((data => {
        this.complaint = data;
        this.dataSource = new MatTableDataSource(this.complaint);
      }));
    }
    else {
      this.complaintService.getByDepartmentId(this.selected).subscribe((data => {
        this.complaint = data;
        this.dataSource = new MatTableDataSource(this.complaint);
      }));
    }
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
  getStatusType(status: string) {
    if (status == "Yeni İstek") {
      return "success";
    }
    else if (status == "Tamamlandı") {
      return "danger"
    }
    else {
      return "metal"
    }
  }

}