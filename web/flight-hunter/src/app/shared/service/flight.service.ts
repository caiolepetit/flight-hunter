import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight } from '../model/flight.model';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  apiUrl: string = 'http://127.0.0.1:8080/flights';
  firstParam: boolean = true;

  constructor(private httpClient: HttpClient) { }

  public getLowestPriceFlight(airportFrom: string, airportTo: string, currency: string): Observable<Flight> {
    let fullApiUrl: string = this.apiUrl + '/lowest-price?';

    fullApiUrl = this.mountFullUrl(fullApiUrl, 'airportFrom', airportFrom.toUpperCase(), this.firstParam); 
    fullApiUrl = this.mountFullUrl(fullApiUrl, 'airportTo', airportTo.toUpperCase(), this.firstParam);
    fullApiUrl = this.mountFullUrl(fullApiUrl, 'currency', currency.toUpperCase(), this.firstParam);

    return this.httpClient.get<Flight>(fullApiUrl);
  }

  private mountFullUrl(actualUrl: string, nameParam:string, param: string, isFirstParam: boolean): string {
    if(param) {
      this.firstParam ? '' : actualUrl += '&';
      this.firstParam = false;
      return actualUrl += nameParam+'='+param;
    }

    return actualUrl;
  }

}