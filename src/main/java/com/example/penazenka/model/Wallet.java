package com.example.penazenka.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wallet {
    private String owner;
    private List<Integer> coins;
    private List<Integer> bills;

    public Wallet(String owner) {
        this.owner = owner;
        this.coins = new ArrayList<>();
        this.bills = new ArrayList<>();
    }

    public void addCoin(int coin) {
        if (this.coins == null) {
            this.coins = new ArrayList<>();
        }
        this.coins.add(coin);
    }

    public void addBill(int bill) {
        if (this.bills == null) {
            this.bills = new ArrayList<>();
        }
        this.bills.add(bill);
    }

    public int findMaxBill() {
        return Collections.max(this.bills);
    }

    public int findMinBill() {
        return Collections.min(this.bills);
    }

    public String getOwner() {
        return owner;
    }

    public List<Integer> getCoins() {
        return coins;
    }

    public List<Integer> getBills() {
        return bills;
    }
}
