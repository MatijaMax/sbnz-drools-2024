import { Arrangement } from "./arrangement";

export interface ArrangementRecommendation {
    arrangementDTO: Arrangement,
    tags: Set<String>,
  }