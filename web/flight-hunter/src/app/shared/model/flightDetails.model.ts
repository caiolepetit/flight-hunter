import { Country } from './country.model';

export class FlightDetails{
    flyFrom: string;
    cityFrom: string;
    countryFrom: Country;
    flyTo: string;
    cityTo: string;
    price: number;
    currency:string;
    countryTo: Country;
}