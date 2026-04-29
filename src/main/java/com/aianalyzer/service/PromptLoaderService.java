package com.aianalyzer.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import com.aianalyzer.model.Lead;
import java.nio.charset.StandardCharsets;

@Service
public class PromptLoaderService {

    @Value("${business.name}")
    private String BUSINESS_NAME;

    /**
     * Loads the lead classification prompt template and replaces the user message placeholder.
     * 
     * @param message The user's message to classify
     * @return The populated prompt ready for AI API call
     */
    public String loadClassificationPrompt(String message) {
        try {
            ClassPathResource resource = new ClassPathResource("prompts/lead-classifier.txt");
            String template = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            return template.replace("{{USER_MESSAGE}}", message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load lead classification prompt file", e);
        }
    }

    /**
     * Loads the email generation prompt template and populates it with lead information.
     * 
     * @param lead The lead object containing all necessary information for email generation
     * @return The populated prompt ready for AI API call
     */
    public String loadEmailPrompt(Lead lead) {
        try {
            ClassPathResource resource = new ClassPathResource("prompts/email.txt");
            String template = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            return template
                    .replace("{{LEAD_NAME}}", lead.getName())
                    .replace("{{INTENT}}", lead.getIntent() != null ? lead.getIntent() : "Not specified")
                    .replace("{{BUDGET}}", lead.getBudget() != null ? lead.getBudget() : "Not specified")
                    .replace("{{URGENCY}}", lead.getUrgency() != null ? lead.getUrgency() : "Not specified")
                    .replace("{{LEAD_SCORE}}", lead.getLeadScore() != null ? lead.getLeadScore() : "Not scored")
                    .replace("{{MESSAGE}}", lead.getMessage())
                    .replace("{{BUSINESS_NAME}}", BUSINESS_NAME);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load email prompt file", e);
        }
    }
}
