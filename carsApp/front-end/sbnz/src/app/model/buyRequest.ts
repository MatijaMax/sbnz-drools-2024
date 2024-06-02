
export interface BuyRequest {
    userId: number;
    userAge: number;
    carId: number;
    numberOfCreditPayments: number;
    useremploymenttype: string;
    employmentStart?: string; // Optional field
    employmentEnd?: string;   // Optional field
  }
  