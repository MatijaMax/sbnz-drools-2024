package sbnz.domain;

import javax.persistence.*;

public class TripReservation {
    private Integer id;

    private ArrangementReservation arrangementReservation;


    private Trip trip;

    private Integer numberOfGuests;

    private Double totalPrice;

    private Double discount;

    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setNewDiscount(Double discount) {
        if(discount < this.discount){
            return;
        }
        this.discount = discount;
    }

    public TripReservation(Integer id, ArrangementReservation arrangementReservation, Trip trip, Integer numberOfGuests, Double totalPrice, Double discount) {
        this.id = id;
        this.arrangementReservation = arrangementReservation;
        this.trip = trip;
        this.numberOfGuests = numberOfGuests;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    public TripReservation() {
        super();
    }

    public TripReservation(ArrangementReservation arrangementReservation, Trip trip, Integer numberOfGuests, Double totalPrice) {
        this.arrangementReservation = arrangementReservation;
        this.trip = trip;
        this.numberOfGuests = numberOfGuests;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrangementReservation getArrangementReservation() {
        return arrangementReservation;
    }

    public void setArrangementReservation(ArrangementReservation arrangementReservation) {
        this.arrangementReservation = arrangementReservation;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscountedPrice(){
        double value = this.totalPrice;

        if(this.totalPrice > 3000){
            value = this.totalPrice*0.05;
        }

        if(this.totalPrice > 2000 && this.trip.getType()== Trip.TRIPTYPE.HISTORY){
            value = this.totalPrice*0.07;
        }

        if(this.numberOfGuests>=2){
            value = this.totalPrice*0.1;
        }

        return value;
    }
    @Override
    public String toString() {
        return "TripReservation{" +
                "id=" + id +
                ", numberOfGuests=" + numberOfGuests +
                ", totalPrice=" + totalPrice +
                ", discount=" + discount +
                '}';
    }
}
