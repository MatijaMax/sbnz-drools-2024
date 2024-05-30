export interface RentRequest {
  scheduleDT: Date;
  cancelDT: Date;
  beginDT: Date;
  returnDT: Date;
  userId: number;
  carId: number;
  cancelReason: string;
  returnState: string;
  isCanceled: boolean;
  isLate: boolean;
}
