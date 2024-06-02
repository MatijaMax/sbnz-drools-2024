export interface RentRequest {
  id: number;
  scheduleDT: Date;
  cancelDT: Date;
  beginDT: Date;
  returnDT: Date;
  userId: number;
  carId: number;
  cancelReason: string;
  returnState: string;
  canceled: boolean;
  late: boolean;
  returnStateHelp: string;
}
