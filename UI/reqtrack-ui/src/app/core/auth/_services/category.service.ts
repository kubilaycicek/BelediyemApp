import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Rx";
import { map } from "rxjs/internal/operators";
import { Category } from '../../model/category.models';
import { CATEGORY_PATH } from '../_models/path.model';
import { HeaderService } from './header.sevice';

@Injectable({ providedIn: 'root' })
export class CategoryService {
    requestOptions:any;

    constructor(private http: HttpClient,private headerService:HeaderService) { }
   
    getAll(): Observable<Category[]> {
        return this.http.get<Category[]>(CATEGORY_PATH + "list/",this.requestOptions = {
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }
    getById(id): Observable<Category> { 
        return this.http.get<Category>(CATEGORY_PATH + id,this.requestOptions = {
        
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }

    update(category: Category): Observable<any> {
        return this.http.put(CATEGORY_PATH, JSON.stringify(category),this.requestOptions = {
        
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
    

    save(category: Category): Observable<any> {
        return this.http.post(CATEGORY_PATH, JSON.stringify(category), this.requestOptions = {
        
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

