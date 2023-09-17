import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AiService {

  private API_URL = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  evaluateCode(code: string) {
    return this.http.post<{result: string}>(`${this.API_URL}/evaluateCode`, code);
  }
}
