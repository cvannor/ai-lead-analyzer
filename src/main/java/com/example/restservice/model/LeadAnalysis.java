package com.example.restservice.model;

public class LeadAnalysis {
    public String intent;
    public int budget;
    public String urgency;
    public String leadScore;

    public LeadAnalysis() {
    }

    public LeadAnalysis(String intent, int budget, String urgency, String leadScore) {
        this.intent = intent;
        this.budget = budget;
        this.urgency = urgency;
        this.leadScore = leadScore;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getLeadScore() {
        return leadScore;
    }

    public void setLeadScore(String leadScore) {
        this.leadScore = leadScore;
    }
}
