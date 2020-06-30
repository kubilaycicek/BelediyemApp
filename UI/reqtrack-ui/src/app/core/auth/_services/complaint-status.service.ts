import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Rx";
import { map } from "rxjs/internal/operators";
import { HeaderService } from './header.sevice';
import { ComplaintStatus } from '../../model/complaint-status.models';
import { COMPLAINTSTATUS_PATH } from '../_models/path.model';

@Injectable({ providedIn: 'root' })
export class ComplaintStatusService {
    requestOptions:any;

    constructor(private http: HttpClient,private headerService:HeaderService) { }
   
    getAll(): Observable<ComplaintStatus[]> {
        return this.http.get<ComplaintStatus[]>(COMPLAINTSTATUS_PATH + "list/",this.requestOptions = {
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }
    getById(id): Observable<ComplaintStatus> { 
        return this.http.get<ComplaintStatus>(COMPLAINTSTATUS_PATH + id,this.requestOptions = {
        
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }

    update(complaintStatus: ComplaintStatus): Observable<any> {
        return this.http.put(COMPLAINTSTATUS_PATH, JSON.stringify(complaintStatus),this.requestOptions = {
        
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
    

    save(complaintStatus: ComplaintStatus): Observable<any> {
        return this.http.post(COMPLAINTSTATUS_PATH, JSON.stringify(complaintStatus), this.requestOptions = {
        
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

