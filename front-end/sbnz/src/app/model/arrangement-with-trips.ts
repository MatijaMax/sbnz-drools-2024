import { Trip } from './trip';

export interface ArrangementWithTrips {
  id: number;
  name: string;
  price: number;
  numberOfGuests: number;
  trips: Trip[];
}
