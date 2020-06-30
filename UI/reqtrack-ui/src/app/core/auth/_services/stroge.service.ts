import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';

declare var require: any;
const SecureStorage = require('secure-web-storage');
const SECRET_KEY = 'token';
@Injectable()
export class StorageService {
constructor() { }
public secureStorage = new SecureStorage(localStorage, {
// Encrypt the localstorage data
encrypt: function encrypt(data) {
    data = CryptoJS.AES.encrypt(data, SECRET_KEY);
    data = data.toString();
    return data;
},
// Decrypt the encrypted data
decrypt: function decrypt(data) {
    data = CryptoJS.AES.decrypt(data, SECRET_KEY);
    data = data.toString(CryptoJS.enc.Utf8);
    return data;
}
});
}