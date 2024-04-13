import { TripReservationCreate } from "./trip-reservation-create";

export interface ArrangementReservationCreate {
    numberOfPeople: number;
    arrangementId: number;
    userId: number;
    tripReservations: TripReservationCreate[];
  }
  