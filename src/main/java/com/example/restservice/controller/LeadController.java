package com.example.restservice.controller;

import com.example.restservice.model.Lead;
import com.example.restservice.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin(origins = "http://localhost:5173")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {
        Lead createdLead = leadService.createLead(lead);

        // After creating the lead, generate and send the email
        leadService.sendEmailToLead(createdLead);


        return ResponseEntity.status(HttpStatus.CREATED).body(createdLead);
    }

    @GetMapping
    public ResponseEntity<Iterable<Lead>> getAllLeads() {
        return ResponseEntity.ok(leadService.getAllLeads());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lead> getLeadById(@PathVariable Long id) {
        Lead lead = leadService.getLeadById(id);
        if (lead != null) {
            return ResponseEntity.ok(lead);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lead> updateLead(@PathVariable Long id, @RequestBody Lead leadDetails) {
        Lead updatedLead = leadService.updateLead(id, leadDetails);
        if (updatedLead != null) {
            return ResponseEntity.ok(updatedLead);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.noContent().build();
    }
}
