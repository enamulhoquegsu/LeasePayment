package com.mine.sharif.newleasepayment;

public class Lease {
    Double credit, account, access, accessFee, cityRide, insurance, lease, income;
    int id;
    String credit_percent, account_percent, access_percent;



    String leaseDate;

    public Lease(String leaseDate, int id, Double credit, Double account, Double access, Double accessFee,
                 Double cityRide, Double insurance, Double lease, Double income, String credit_percent, String account_percent, String access_percent) {
        this.credit = credit;
        this.account = account;
        this.access = access;
        this.accessFee = accessFee;
        this.cityRide = cityRide;
        this.insurance = insurance;
        this.lease = lease;
        this.income = income;
        this.leaseDate = leaseDate;
        this.id = id;
        this.credit_percent = credit_percent;
        this.account_percent = account_percent;
        this.access_percent = access_percent;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public Double getAccess() {
        return access;
    }

    public void setAccess(Double access) {
        this.access = access;
    }

    public Double getAccessFee() {
        return accessFee;
    }

    public void setAccessFee(Double accessFee) {
        this.accessFee = accessFee;
    }

    public Double getCityRide() {
        return cityRide;
    }

    public void setCityRide(Double cityRide) {
        this.cityRide = cityRide;
    }

    public Double getInsurance() {
        return insurance;
    }

    public void setInsurance(Double insurance) {
        this.insurance = insurance;
    }

    public Double getLease() {
        return lease;
    }

    public void setLease(Double lease) {
        this.lease = lease;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(String leaseDate) {
        this.leaseDate = leaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCredit_percent() {
        return credit_percent;
    }

    public void setCredit_percent(String credit_percent) {
        this.credit_percent = credit_percent;
    }

    public String getAccount_percent() {
        return account_percent;
    }

    public void setAccount_percent(String account_percent) {
        this.account_percent = account_percent;
    }

    public String getAccess_percent() {
        return access_percent;
    }

    public void setAccess_percent(String access_percent) {
        this.access_percent = access_percent;
    }
}
