package com.example.planosycentellas.model;

import java.util.ArrayList;
import java.util.List;

public class PatreonAwards {

    private String initialMessage;
    private List<String> awardsDetails;

    public PatreonAwards(){
        initialMessage = "";
        awardsDetails = new ArrayList<>();
    }

    public void setAwardsDetails(List<String> awardsDetails) {
        this.awardsDetails = awardsDetails;
    }

    public void setInitialMessage(String initialMessage) {
        this.initialMessage = initialMessage;
    }

    public List<String> getAwardsDetails() {
        return awardsDetails;
    }

    public String getInitialMessage() {
        return initialMessage;
    }
}
