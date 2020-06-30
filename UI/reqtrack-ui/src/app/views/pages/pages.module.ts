// Angular
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
// Partials
import { PartialsModule } from '../partials/partials.module';
// Pages
import { Ng2TableModule } from 'ng2-table/ng2-table';
import { LightboxModule } from 'ngx-lightbox';
import { CoreModule } from '../../core/core.module';
import { MyPageComponent } from './my-page/my-page.component';
import { UserAddComponent } from './user-add/user-add.component';
import { MatInputModule, MatAutocompleteModule, MatIconModule, MatSliderModule, MatCheckboxModule, MatFormFieldModule, MatSlideToggleModule, MatButtonModule, MatTableModule, MatPaginatorModule, MatSelectModule, MatMenuModule, MatSortModule, MatProgressSpinnerModule, MatTooltipModule, MatSortHeader } from '@angular/material';
import { CategoryAddComponent } from './category-add/category-add.component';
import { DepartmentAddComponent } from './department-add/department-add.component';
import { UserListComponent } from './user-list/user-list.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { DepartmentListComponent } from './department-list/department-list.component';
import { ViewComplaintComponent } from './view-complaint/view-complaint.component';
import { AgmCoreModule } from '@agm/core';
import { MaterialPreviewModule } from '../partials/content/general/material-preview/material-preview.module';
import { ComplaintListComponent } from './complaint-list/complaint-list.component';
import { PaginationModule } from 'ng2-bootstrap/pagination';
import { UserTypeListComponent } from './userType-list/userType-list.component';
import { UserTypeAddComponent } from './userType-add/userType-add.component';
import { NgbAlert, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { CategoryUpdateComponent } from './category-update/category-update.component';
import { AngularYandexMapsModule } from 'angular8-yandex-maps';
import { DepartmentUpdateComponent } from './department-update/department-update.component';
import { UserTypeUpdateComponent } from './userType-update/userType-update.component';
import { UserUpdateComponent } from './user-update/user-update.component';

@NgModule({
	declarations: [
		MyPageComponent,
		UserAddComponent,
		CategoryAddComponent,
		DepartmentAddComponent,
		UserListComponent,
		CategoryListComponent,
		DepartmentListComponent,
		ViewComplaintComponent,
		ComplaintListComponent,
		UserTypeListComponent,
		UserTypeAddComponent,
		CategoryUpdateComponent,
		DepartmentUpdateComponent,
		UserTypeUpdateComponent,
		UserUpdateComponent],
	exports: [],
	imports: [
		CommonModule,
		AgmCoreModule,
		AngularYandexMapsModule,
		NgbAlertModule,
		HttpClientModule,
		FormsModule,
		CoreModule,
		LightboxModule,
		PartialsModule,
		MatInputModule,
		MatAutocompleteModule,
		MatIconModule,
		MatSliderModule,
		MatCheckboxModule,
		MatFormFieldModule,
		MatSlideToggleModule,
		MatButtonModule,
		MatTableModule,
		MatPaginatorModule,
		MatSelectModule,
		MatTooltipModule,
		MaterialPreviewModule,
		Ng2TableModule,
		ReactiveFormsModule,
		RouterModule,
		MatSortModule,
		PaginationModule.forRoot()
	],
	providers: []
})
export class PagesModule {
}
