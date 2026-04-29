package com.aianalyzer.service;

import com.aianalyzer.model.Lead;
import com.aianalyzer.model.LeadAnalysis;
import com.aianalyzer.repository.LeadRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private AiService aiService;

    @Autowired
    private EmailService emailService;

    @Value("${internal.email}")
    private String INTERNAL_EMAIL;

    public Lead createLead(Lead lead) {
        // Call AI service to classify the lead
        System.out.println("\nInitiating AI lead classification for new lead: " + lead.getName());
        LeadAnalysis analysis = aiService.classifyLead(lead.getMessage());
        
        // Set the analysis data on the lead
        lead.setIntent(analysis.getIntent());
        lead.setBudget(analysis.getBudget());
        lead.setUrgency(analysis.getUrgency());
        lead.setLeadScore(analysis.getLeadScore());
        
        System.out.println("AI Analysis - Intent: " + analysis.getIntent() + 
                         ", Budget: " + analysis.getBudget() + 
                         ", Urgency: " + analysis.getUrgency() + 
                         ", LeadScore: " + analysis.getLeadScore());
        
        return leadRepository.save(lead);
    }

    public Lead getLeadById(Long id) {
        return leadRepository.findById(id).orElse(null);
    }

    public Iterable<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    public Lead updateLead(Long id, Lead leadDetails) {
        Lead lead = leadRepository.findById(id).orElse(null);
        if (lead != null) {
            if (leadDetails.getName() != null) lead.setName(leadDetails.getName());
            if (leadDetails.getEmail() != null) lead.setEmail(leadDetails.getEmail());
            if (leadDetails.getMessage() != null) lead.setMessage(leadDetails.getMessage());
            if (leadDetails.getStatus() != null) lead.setStatus(leadDetails.getStatus());
            return leadRepository.save(lead);
        }
        return null;
    }

    public void sendEmailToLead(Lead lead) {
        //External email
        JsonNode emailContent = aiService.generateContextualEmail(lead);

        System.out.println("\nTriggering email for lead: " + lead.getName());
        emailService.sendEmail(lead.getEmail(), emailContent);

        //Internal email to sales team
        sendInternalEmail(lead);
    }

    private void sendInternalEmail(Lead lead) {
        String internalEmailBody = "New lead created with the following details:\n\n" +
                "Name: " + lead.getName() + "\n\n" +
                "Email: " + lead.getEmail() + "\n\n" +
                "Message: " + lead.getMessage() + "\n\n" +
                "Intent: " + lead.getIntent() + "\n\n" +
                "Budget: " + lead.getBudget() + "\n\n" +
                "Urgency: " + lead.getUrgency() + "\n\n" +
                "Lead Score: " + lead.getLeadScore();

        String internalEmailSubject = "New Lead: " + lead.getName() + " - " + lead.getIntent();
        
        //email content should be in the format: {"emailBody": "...", "subject": "..."}
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode email = mapper.createObjectNode();
        
        email.put("emailBody", internalEmailBody);
        email.put("subject", internalEmailSubject);
        
        System.out.println("\nTriggering internal email:");   
        emailService.sendEmail(INTERNAL_EMAIL, email);
       
    }

    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }
}
