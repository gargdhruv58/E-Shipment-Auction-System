package com.example.e_shipmentauctionsystem1;

public class Product {
    String prID;
    String prWeight;
    String prLength;
    String prWidth;
    String prHeight;
    String prDesc;
    String prPickup;
    String imageUrl;
    String startBid;
    String prEmail;

    public Product() {
        //empty constructor

    }

    public Product(String prID, String prWeight, String prLength, String prWidth, String prHeight,String prDesc,String prPickup,String startBid,String imageUrl,String prEmail) {
        this.prID = prID;
        this.prWeight = prWeight;
        this.prLength = prLength;
        this.prWidth = prWidth;
        this.prHeight = prHeight;
        this.prDesc= prDesc;
        this.prPickup = prPickup;
        this.startBid = startBid;
        this.imageUrl = imageUrl;
        this.prEmail=prEmail;
    }

    public String getPrID() {
        return prID;
    }

    public String getPrWeight() {
        return prWeight;
    }

    public String getPrLength() {
        return prLength;
    }

    public String getPrWidth() {
        return prWidth;
    }

    public String getPrHeight() {
        return prHeight;
    }

    public String getPrDesc() {
        return prDesc;
    }

    public String getPrPickup() {
        return prPickup;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStartBid() {
        return startBid;
    }

    public String getPrEmail() {
        return prEmail;
    }
}
