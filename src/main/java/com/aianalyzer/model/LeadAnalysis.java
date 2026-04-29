package com.aianalyzer.model;

public class LeadAnalysis {
    public String intent;
    public String budget;
    public String urgency;
    public String leadScore;

    public LeadAnalysis() {
    }

    public LeadAnalysis(String intent, String budget, String urgency, String leadScore) {
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

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
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
