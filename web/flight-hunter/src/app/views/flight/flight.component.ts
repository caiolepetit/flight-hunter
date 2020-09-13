import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { FlightDetails } from 'src/app/shared/model/flightDetails.model';
import { FlightService } from 'src/app/shared/service/flight.service';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent implements OnInit {

  public flightSearchForm: FormGroup;
  lowestPriceFlight: FlightDetails;
  currencyHtml:string;
  showProgressBar: boolean = false;
  showResultCard: boolean = false;
  showErrorMessageAboutInformations: boolean = false;

  constructor( 
    private formBuilder: FormBuilder,
    public flightService: FlightService) { }

  ngOnInit(): void {
    this.flightSearchForm = this.formBuilder.group({
      airportFrom: [''] ,
      airportTo: [''],
      currency: ['']      
    })
  }

  getLowestPriceFlight() {
    this.showProgressBar = true;
    let {airportFrom, airportTo, currency} = this.flightSearchForm.value;

    if(this.checkInformationIsValid(airportFrom, airportTo)) {
      this.flightService.getLowestPriceFlight(airportFrom, airportTo, currency).subscribe(result => {
        this.lowestPriceFlight = result.flight;
        this.showResultCard = true;
        this.showProgressBar = false;
      });
      this.currencyHtml = currency;
    } else {
      this.showErrorMessageAboutInformations = true;
      this.showProgressBar = false;
    }
  }

  private checkInformationIsValid(airportFrom: string, airportTo: string) {
    if(!airportFrom && !airportTo) {
      return false;
    }
    return true;
  }

  closeAlert() {
    this.showErrorMessageAboutInformations = false;
  }

}