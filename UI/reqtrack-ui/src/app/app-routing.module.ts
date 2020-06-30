// Angular
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// Components
import { BaseComponent } from './views/theme/base/base.component';
import { ErrorPageComponent } from './views/theme/content/error-page/error-page.component';
// Auth
import { AuthGuard } from './core/auth';
import { MyPageComponent } from './views/pages/my-page/my-page.component';
import { UserAddComponent } from './views/pages/user-add/user-add.component';
import { CategoryAddComponent } from './views/pages/category-add/category-add.component';
import { DepartmentAddComponent } from './views/pages/department-add/department-add.component';
import { UserListComponent } from './views/pages/user-list/user-list.component';
import { CategoryListComponent } from './views/pages/category-list/category-list.component';
import { DepartmentListComponent } from './views/pages/department-list/department-list.component';
import { ViewComplaintComponent } from './views/pages/view-complaint/view-complaint.component';
import { ComplaintListComponent } from './views/pages/complaint-list/complaint-list.component';
import { UserTypeListComponent } from './views/pages/userType-list/userType-list.component';
import { UserTypeAddComponent } from './views/pages/userType-add/userType-add.component';
import { CategoryUpdateComponent } from './views/pages/category-update/category-update.component';
import { DepartmentUpdateComponent } from './views/pages/department-update/department-update.component';
import { UserTypeUpdateComponent } from './views/pages/userType-update/userType-update.component';
import { UserUpdateComponent } from './views/pages/user-update/user-update.component';

const routes: Routes = [
	{path: 'auth', loadChildren: () => import('../app/views/pages/auth/auth.module').then(m => m.AuthModule)},

	{
		path: '',
		component: BaseComponent,
		canActivate: [AuthGuard],
		children: [
			{
				path: 'my-page', component: MyPageComponent 
		  	},
			{
				path: 'user-add', component: UserAddComponent 
			},
			{
				path: 'user-update/:id', component: UserUpdateComponent 
			},
			{
				path: 'category-add', component: CategoryAddComponent 
			},
			{
				path: 'category-update/:id', component: CategoryUpdateComponent 
			},
			{
				path: 'department-add', component: DepartmentAddComponent 
			},
			{
				path: 'department-update/:id', component: DepartmentUpdateComponent 
			},
			{
				path: 'user-list', component: UserListComponent 
			},
			{
				path: 'category-list', component: CategoryListComponent 
			},
			{
				path: 'department-list', component: DepartmentListComponent 
			},
			{
				path: 'view-complaint/:id', component: ViewComplaintComponent 
			},
			{
				path: 'complaint-list', component: ComplaintListComponent 
			},
			{
				path: 'userType-list', component: UserTypeListComponent 
			},
			{
				path: 'userType-add', component: UserTypeAddComponent 
			},
			{
				path: 'userType-update/:id', component: UserTypeUpdateComponent 
			},
			{
				path: 'dashboard',
				loadChildren: () => import('../app/views/pages/dashboard/dashboard.module').then(m => m.DashboardModule)
			},
			{
				path: 'error/403',
				component: ErrorPageComponent,
				data: {
					'type': 'error-v6',
					'code': 403,
					'title': '403... Access forbidden',
					'desc': 'Looks like you don\'t have permission to access for requested page.<br> Please, contact administrator'
				}
			},
			{path: 'error/:type', component: ErrorPageComponent},
			{path: '', redirectTo: 'user-list', pathMatch: 'full'},
			{path: '**', redirectTo: 'user-list', pathMatch: 'full'}
		]
	},

	{path: '**', redirectTo: 'error/403', pathMatch: 'full'},
];

@NgModule({
	imports: [
		RouterModule.forRoot(routes)
	],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
