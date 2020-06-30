import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Rx";
import { map } from "rxjs/internal/operators";
import { USER_PATH } from '../_models/path.model';
import { HeaderService } from './header.sevice';
import { Users } from '../../model/user.models';

@Injectable({ providedIn: 'root' })
export class UserService {
    requestOptions:any;
    constructor(private http: HttpClient,private headerService:HeaderService) { }
   
    getAll(): Observable<Users[]> {
        return this.http.get<Users[]>(USER_PATH + "list/",this.requestOptions = {
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }
    getById(id): Observable<Users> { 
        return this.http.get<Users>(USER_PATH + id,this.requestOptions = {
        
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }

    update(user: Users): Observable<any> {
        return this.http.put(USER_PATH, JSON.stringify(user),this.requestOptions = {
        
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
    

    save(user: Users): Observable<any> {
        return this.http.post(USER_PATH, JSON.stringify(user), this.requestOptions = {
        
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

