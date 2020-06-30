import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/Rx";
import { map } from "rxjs/internal/operators";
import { HeaderService } from './header.sevice';
import { Complaint } from '../../model/complaint.models';
import { COMPLAINT_PATH } from '../_models/path.model';
import { ComplaintUpdateModel } from '../../model/complaintUpdate.model';

@Injectable({ providedIn: 'root' })
export class ComplaintService {
    requestOptions:any;

    constructor(private http: HttpClient,private headerService:HeaderService) { }
   
    getAll(): Observable<Complaint[]> {
        return this.http.get<Complaint[]>(COMPLAINT_PATH + "list/",this.requestOptions = {
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }
    getById(id): Observable<Complaint> { 
        return this.http.get<Complaint>(COMPLAINT_PATH + id,this.requestOptions = {
        
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }
    getByDepartmentId(id): Observable<Complaint[]> { 
        return this.http.get<Complaint[]>(COMPLAINT_PATH+"list/department/" + id,this.requestOptions = {
        
            headers: new HttpHeaders(this.headerService.getHeader()),
        });
    }
    update(complaintUpdate: ComplaintUpdateModel): Observable<any> {
        return this.http.put(COMPLAINT_PATH+"updateStatusAndCategory", JSON.stringify(complaintUpdate),this.requestOptions = {
        
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
    

    save(complaint: Complaint): Observable<any> {
        return this.http.post(COMPLAINT_PATH, JSON.stringify(complaint), this.requestOptions = {
        
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

