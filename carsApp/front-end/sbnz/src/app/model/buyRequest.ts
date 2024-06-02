
export interface BuyRequest {
    id: number;
    userId: number;
    userAge: number;
    carId: number;
    numberOfCreditPayments: number;
    useremploymenttype: string;
    employmentStart?: string; // Optional field
    employmentEnd?: string;   // Optional field
    leftToPay: number;
  }
  