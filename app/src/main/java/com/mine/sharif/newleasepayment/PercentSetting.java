package com.mine.sharif.newleasepayment;

public class PercentSetting
{
    String creditPercent, accountPercent, accessPercent;

    public PercentSetting(String creditPercent, String accountPercent, String accessPercent) {
        this.creditPercent = creditPercent;
        this.accountPercent = accountPercent;
        this.accessPercent = accessPercent;
    }

    public String getCreditPercent() {
        return creditPercent;
    }

    public void setCreditPercent(String creditPercent) {
        this.creditPercent = creditPercent;
    }

    public String getAccountPercent() {
        return accountPercent;
    }

    public void setAccountPercent(String accountPercent) {
        this.accountPercent = accountPercent;
    }

    public String getAccessPercent() {
        return accessPercent;
    }

    public void setAccessPercent(String accessPercent) {
        this.accessPercent = accessPercent;
    }
}
