package sbnz.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="RENTREQUEST")
public class RentRequest {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="scheduleDT")
    LocalDateTime scheduleDT;
    @Column(name="cancelDT")
    LocalDateTime cancelDT;
    @Column(name="beginDT")
    LocalDateTime beginDT;
    @Column(name="returnDT")
    LocalDateTime returnDT;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    Car car;
    @Column(name="cancelReason")
    String cancelReason;
    //ovo ce trebati mozda enum (ok, neispravno, pokvario/udario, neuredno)
    @Column(name="returnState")
    String returnState;
    @Column(name="isCanceled")
    Boolean isCanceled;
    @Column(name="isLate")
    Boolean isLate;

    public RentRequest() { }

    public RentRequest(Long id, LocalDateTime scheduleDT, LocalDateTime cancelDT, LocalDateTime beginDT, LocalDateTime returnDT, User user, Car car, String cancelReason, String returnState, Boolean isCanceled, Boolean isLate) {
        this.id = id;
        this.scheduleDT = scheduleDT;
        this.cancelDT = cancelDT;
        this.beginDT = beginDT;
        this.returnDT = returnDT;
        this.user = user;
        this.car = car;
        this.cancelReason = cancelReason;
        this.returnState = returnState;
        this.isCanceled = isCanceled;
        this.isLate = isLate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getScheduleDT() {
        return scheduleDT;
    }

    public void setScheduleDT(LocalDateTime scheduleDT) {
        this.scheduleDT = scheduleDT;
    }

    public LocalDateTime getCancelDT() {
        return cancelDT;
    }

    public void setCancelDT(LocalDateTime cancelDT) {
        this.cancelDT = cancelDT;
    }

    public LocalDateTime getBeginDT() {
        return beginDT;
    }

    public void setBeginDT(LocalDateTime beginDT) {
        this.beginDT = beginDT;
    }

    public LocalDateTime getReturnDT() {
        return returnDT;
    }

    public void setReturnDT(LocalDateTime returnDT) {
        this.returnDT = returnDT;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getReturnState() {
        return returnState;
    }

    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public Boolean getLate() {
        return isLate;
    }

    public void setLate(Boolean late) {
        isLate = late;
    }

    public boolean canceledAfterOneDay() {
        return cancelDT != null && scheduleDT != null && !scheduleDT.plusDays(1).isBefore(cancelDT);
    }

    public boolean canceledOneHourBeforeBeginning() {
        return cancelDT != null && beginDT != null && !cancelDT.plusHours(1).isBefore(beginDT);
    }

    public boolean isBrokenOrHit() {
        return returnState.equals("broken") || returnState.equals("hit");
    }
}
