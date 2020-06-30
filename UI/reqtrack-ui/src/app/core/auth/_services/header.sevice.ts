import { HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import * as _ from 'lodash';
import { BehaviorSubject } from 'rxjs/Rx';

@Injectable({ providedIn: 'root' })
export class HeaderService {
    tokens:any;
    headerDict :any;
    requestOptions :any;
    public getHeader(){
        this.headerDict= {
            'Authorization':'Bearer ' + localStorage.getItem("token"),
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'charset': 'utf-8',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
        }
        this.requestOptions = {
        
            headers: new HttpHeaders(this.headerDict),
        };
        return this.headerDict;
    }
}