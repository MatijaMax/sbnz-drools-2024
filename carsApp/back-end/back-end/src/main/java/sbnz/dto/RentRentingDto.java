package sbnz.dto;

import sbnz.domain.Car;
import sbnz.domain.RentRequest;
import sbnz.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

public class RentRentingDto {
    Long id;
    LocalDateTime scheduleDT;
    LocalDateTime cancelDT;
    LocalDateTime beginDT;
    LocalDateTime returnDT;
    Integer userId;
    Integer carId;
    String cancelReason;
    String returnState;
    Boolean isCanceled;
    Boolean isLate;

    public RentRentingDto(Long id, LocalDateTime scheduleDT, LocalDateTime cancelDT, LocalDateTime beginDT, LocalDateTime returnDT, Integer userId, Integer carId, String cancelReason, String returnState, Boolean isCanceled, Boolean isLate) {
        this.id = id;
        this.scheduleDT = scheduleDT;
        this.cancelDT = cancelDT;
        this.beginDT = beginDT;
        this.returnDT = returnDT;
        this.userId = userId;
        this.carId = carId;
        this.cancelReason = cancelReason;
        this.returnState = returnState;
        this.isCanceled = isCanceled;
        this.isLate = isLate;
    }

    public RentRentingDto(RentRequest r) {
        this.id = r.getId();
        this.scheduleDT = r.getScheduleDT();
        this.cancelDT = r.getCancelDT();
        this.beginDT = r.getBeginDT();
        this.returnDT = r.getReturnDT();
        this.userId = r.getUser().getId();
        this.carId = r.getCar().getId();
        this.cancelReason = r.getCancelReason();
        this.returnState = r.getReturnState();
        this.isCanceled = r.getCanceled();
        this.isLate = r.getLate();
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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
}
