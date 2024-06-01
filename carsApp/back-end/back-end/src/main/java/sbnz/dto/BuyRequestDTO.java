package sbnz.dto;

import sbnz.domain.BuyRequest;

import java.time.LocalDate;

public class BuyRequestDTO {
    private Integer id;
    private Integer userId;
    private Integer userAge;
    private Integer carId;
    private Integer numberOfCreditPayments;
    private String useremploymenttype;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;

    public BuyRequestDTO() {
    }

    public BuyRequestDTO(Integer id, Integer userId, Integer userAge, Integer carId, Integer numberOfCreditPayments, String useremploymenttype, LocalDate employmentStart, LocalDate employmentEnd) {
        this.id = id;
        this.userId = userId;
        this.userAge = userAge;
        this.carId = carId;
        this.numberOfCreditPayments = numberOfCreditPayments;
        this.useremploymenttype = useremploymenttype;
        this.employmentStart = employmentStart;
        this.employmentEnd = employmentEnd;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getNumberOfCreditPayments() {
        return numberOfCreditPayments;
    }

    public void setNumberOfCreditPayments(Integer numberOfCreditPayments) {
        this.numberOfCreditPayments = numberOfCreditPayments;
    }

    public String getUseremploymenttype() {
        return useremploymenttype;
    }

    public void setUseremploymenttype(String useremploymenttype) {
        this.useremploymenttype = useremploymenttype;
    }

    public LocalDate getEmploymentStart() {
        return employmentStart;
    }

    public void setEmploymentStart(LocalDate employmentStart) {
        this.employmentStart = employmentStart;
    }

    public LocalDate getEmploymentEnd() {
        return employmentEnd;
    }

    public void setEmploymentEnd(LocalDate employmentEnd) {
        this.employmentEnd = employmentEnd;
    }

    public BuyRequestDTO(BuyRequest buyRequest) {
        this.setId(buyRequest.getId());
        this.setUserId(buyRequest.getUser().getId());
        this.setUserAge(buyRequest.getUserAge());
        this.setCarId(buyRequest.getCar().getId());
        this.setNumberOfCreditPayments(buyRequest.getNumberOfCreditPayments());
        this.setUseremploymenttype(buyRequest.getUseremploymenttype().toString());
        this.setEmploymentStart(buyRequest.getEmploymentStart());
        this.setEmploymentEnd(buyRequest.getEmploymentEnd());
    }
}
