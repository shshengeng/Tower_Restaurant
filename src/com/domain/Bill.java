package com.domain;

import java.time.LocalDateTime;

public class Bill {
    private Integer id;
    private String billId;
    private Integer dishId;
    private Integer nums;
    private Double money;
    private Integer diningTable;
    private LocalDateTime billDate;
    private String state;

    public Bill(){}

    public Bill(Integer id, String billId, Integer dishId, Integer nums, Double money, Integer diningTable, LocalDateTime billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.dishId = dishId;
        this.nums = nums;
        this.money = money;
        this.diningTable = diningTable;
        this.billDate = billDate;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(Integer diningTable) {
        this.diningTable = diningTable;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
