import { TripReservationCreate } from "./trip-reservation-create";

export interface ArrangementReservationCreate {
    id: number;
    arrangementId: number;
    userId: number;
    numberOfPeople: number;
    tripReservations: TripReservationCreate[];
  }
  