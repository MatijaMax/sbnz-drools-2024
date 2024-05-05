import { Trip } from './trip';

export interface ArrangementWithTrips {
  id: number;
  name: string;
  price: number;
  location: string;
  numberOfGuests: number;
  trips: Trip[];
  averageGrade: number;
}
