import { TripReservationResponseDTO } from "./trip-reservation-responce";

export class ArrangementReservationResponseDTO {
  id: number;
  arrangementId: number;
  userId: number;
  numberOfPeople: number;
  totalPrice: number;
  tripReservations: TripReservationResponseDTO[];
}
