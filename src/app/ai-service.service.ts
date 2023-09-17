import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AiService {

  private API_URL = 'http://localhost:8080'; // Replace with your actual backend endpoint

  constructor(private http: HttpClient) { }

  evaluateCode(code: string) {
    return this.http.post<string>(`${this.API_URL}/evaluateCode`, { code: code });
  }
}
