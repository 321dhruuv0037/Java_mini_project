package com.example.demo;


public class table {
    String id;
    String type;
    String invested;
    String rate;
    String time;
    String returns;

    public table(String id, String type, String invested, String rate, String time, String returns) {
        this.id = id;
        this.type = type;
        this.invested = invested;
        this.rate = rate;
        this.time = time;
        this.returns = returns;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInvested() {
        return invested;
    }

    public void setInvested(String invested) {
        this.invested = invested;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReturns() {
        return returns;
    }

    public void setReturns(String returns) {
        this.returns = returns;
    }
}