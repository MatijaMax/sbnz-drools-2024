import { TripReservationCreate } from "./trip-reservation-create";

export interface ArrangementReservationCreate {
    id: number;
    numberOfPeople: number;
    arrangementId: number;
    userId: number;
    tripReservations: TripReservationCreate[];
  }
  