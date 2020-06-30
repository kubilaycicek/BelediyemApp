import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Rx";
import { map } from "rxjs/internal/operators";
import { DEPARTMENT_PATH } from '../_models/path.model';
import { HeaderService } from './header.sevice';
import { Department } from '../../model/department.models';

@Injectable({ providedIn: 'root' })
export class DepartmentService {
    requestOptions:any;

    constructor(private http: HttpClient,private headerService:HeaderService) { }
   
    getAll(): Observable<Department[]> {
        return this.http.get<Department[]>(DEPARTMENT_PATH + "list/",this.requestOptions = {
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }
    getById(id): Observable<Department> { 
        return this.http.get<Department>(DEPARTMENT_PATH + id,this.requestOptions = {
        
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }

    update(department: Department): Observable<any> {
        return this.http.put(DEPARTMENT_PATH, JSON.stringify(department),this.requestOptions = {
        
            headers: new HttpHeaders(this.headerService.getHeader()),
        }).pipe(map(
            res => {
                if (res) {
                    return res;
                } else {
                    return {};
                }
            }
        ));
    }
    
    save(department: Department): Observable<any> {
        return this.http.post(DEPARTMENT_PATH, JSON.stringify(department), this.requestOptions = {
        
            headers: new HttpHeaders(this.headerService.getHeader()),
        }).pipe(map(
            res => {
                if (res) {
                    return res;
                } else {
                    return {};
                }
            }
        ));
    }
}

