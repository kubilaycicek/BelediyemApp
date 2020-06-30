import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Rx";
import { map } from "rxjs/internal/operators";
import { USERTYPE_PATH } from '../_models/path.model';
import { HeaderService } from './header.sevice';
import { UserType } from '../../model/userType.models';

@Injectable({ providedIn: 'root' })
export class UserTypeSevice {
    requestOptions:any;

    constructor(private http: HttpClient,private headerService:HeaderService) { }
   
    getAll(): Observable<UserType[]> {
        return this.http.get<UserType[]>(USERTYPE_PATH + "list/",this.requestOptions = {
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }
    getById(id): Observable<UserType> { 
        return this.http.get<UserType>(USERTYPE_PATH + id,this.requestOptions = {
        
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }

    update(userType: UserType): Observable<any> {
        return this.http.put(USERTYPE_PATH, JSON.stringify(userType),this.requestOptions = {
        
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
    

    save(userType: UserType): Observable<any> {
        return this.http.post(USERTYPE_PATH, JSON.stringify(userType), this.requestOptions = {
        
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

