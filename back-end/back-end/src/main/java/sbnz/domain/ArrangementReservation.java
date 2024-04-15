package sbnz.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "arrangement_reservation", schema = "isa")
public class ArrangementReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrangement_id", nullable = false)
    private Arrangement arrangement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "number_of_people", nullable = false)
    private Integer numberOfPeople;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "arrangement_price", nullable = false)
    private Double arrangementPrice;

    @Column(name = "trip_price", nullable = false)
    private Double tripPrice;

    @OneToMany(mappedBy = "arrangementReservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TripReservation> tripReservations = new HashSet<TripReservation>();

    public ArrangementReservation() {
        super();
    }

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

    public ArrangementReservation(Integer id, Arrangement arrangement, User user, Integer numberOfPeople, Double totalPrice, Double discount, Double arrangementPrice, Double tripPrice, Set<TripReservation> tripReservations) {
        this.id = id;
        this.arrangement = arrangement;
        this.user = user;
        this.numberOfPeople = numberOfPeople;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.arrangementPrice = arrangementPrice;
        this.tripPrice = tripPrice;
        this.tripReservations = tripReservations;
    }

    public ArrangementReservation(Arrangement arrangement, User user, Integer numberOfPeople, Double arrangementPrice, Double tripPrice, double totalPrice) {
        this.arrangement = arrangement;
        this.user = user;
        this.numberOfPeople = numberOfPeople;
        this.arrangementPrice = arrangementPrice;
        this.tripPrice = tripPrice;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Set<TripReservation> getTripReservations() {
        return tripReservations;
    }

    public void setTripReservations(Set<TripReservation> tripReservations) {
        this.tripReservations = tripReservations;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getArrangementPrice() {
        return arrangementPrice;
    }

    public void setArrangementPrice(Double arrangementPrice) {
        this.arrangementPrice = arrangementPrice;
    }

    public Double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(Double tripPrice) {
        this.tripPrice = tripPrice;
    }

    public void changePrice(){
        this.totalPrice=totalPrice+100;
        System.out.println("Price: " + this.totalPrice);
    }

    public void changeReservationPriceRuleOne(){
        this.arrangementPrice = (this.numberOfPeople*this.arrangement.getPrice())*0.1;
        System.out.println("Arrangement price: " + this.arrangementPrice);
    }

    public void changeReservationPriceRuleTwo(){
        this.arrangementPrice = (this.numberOfPeople*this.arrangement.getPrice())*0.15;
        System.out.println("Arrangement price: " + this.arrangementPrice);
    }

    public void calculateTotalPrice(double totalTripPrice){
        this.tripPrice = totalTripPrice;
        this.totalPrice= this.tripPrice + this.arrangementPrice;

        System.out.println("Total arrangement price: " + this.arrangementPrice);
        System.out.println("Total trip price: " + this.tripPrice);
        System.out.println("Total price: " + this.totalPrice);
    }
    @Override
    public String toString() {
        return "ArrangementReservation{" +
                "id=" + id +
                ", arrangement=" + arrangement +
                ", user=" + user +
                ", numberOfPeople=" + numberOfPeople +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                ", arrangementPrice=" + arrangementPrice +
                ", tripPrice=" + tripPrice +
                ", tripReservations=" + tripReservations +
                '}';
    }

    public int getTripReservationsSize() {
        return this.tripReservations.size();
    }

    public void deleteArrangementDiscount(){
        this.discount = 0.0;
        this.tripPrice = 0.0;
        for (TripReservation tr: this.tripReservations){
            this.tripPrice += tr.getTotalPrice() * (1-tr.getDiscount());
        }
        this.totalPrice = this.arrangementPrice + this.tripPrice;
    }

    public void deleteTripDiscount(){
        this.tripPrice = 0.0;
        for (TripReservation tr: this.tripReservations){
            tr.setDiscount(0.0);
            this.tripPrice += tr.getTotalPrice();
        }
        this.totalPrice = this.arrangementPrice * (1 - this.getDiscount()) + this.tripPrice;
    }
}
