import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { environment } from './../environments/environment';
import {Observable} from "rxjs";


export interface Level{
  name: string,
  story: string,
  theory: string,
  task: string,
  timeLimit: number,
  solution: string
}
@Injectable({
  providedIn: 'root'
})
export class LevelService {
  _indexLevel?: string;

  set indexLevel(value: string) {
    this._indexLevel = value;
  }

  constructor(private client : HttpClient) {  }

  getLevel(): Observable<Level>{
    return this.client.get<Level>(environment.baseUrl + "/level/" + this._indexLevel);
  }

}
