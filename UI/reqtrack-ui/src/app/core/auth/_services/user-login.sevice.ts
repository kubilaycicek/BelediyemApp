import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from "rxjs/Rx";
import { map } from 'rxjs/operators';
import { LoginUser } from '../_models/login-user.model';
import { LOGIN_PATH } from '../_models/path.model';

@Injectable({ providedIn: 'root' })
export class UserLoginService {
  headerDict = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'charset': 'utf-8',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
    'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
  }
  requestOptions = {
    headers: new HttpHeaders(this.headerDict),
  };
  constructor(private http: HttpClient) { }
  login(login: LoginUser): Observable<any> {
    return this.http.post(LOGIN_PATH, JSON.stringify(login), this.requestOptions).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  getToken() {

  }
}

