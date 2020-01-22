package com.example.e_shipmentauctionsystem1;


public class Bidder {
    String BidId;
    String bid;
    String userId;

    public Bidder() {

    }

    public Bidder(String bidId, String bid,String userId) {
        this.BidId = bidId;
        this.bid = bid;
        this.userId=userId;
        //this.Bname=bname;
    }

    public String getBidId() {
        return BidId;
    }

    public String getBid() {
        return bid;
    }

    public String getUserId() {
        return userId;
    }
}
