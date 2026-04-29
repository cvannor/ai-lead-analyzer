package com.aianalyzer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

import com.aianalyzer.model.Lead;
import com.aianalyzer.model.LeadAnalysis;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import java.util.Map;
import java.util.List;

@Service
public class AiService {

    @Value("${openai.api.key}")
    private String API_KEY;

    @Autowired
    private PromptLoaderService promptLoaderService;

    public LeadAnalysis classifyLead(String message) {
        String prompt = promptLoaderService.loadClassificationPrompt(message);

        ResponseEntity<String> response = callOpenAiApi(prompt);

        return parseLeadAnalysis(response.getBody());

    }

    private ResponseEntity<String> callOpenAiApi(String prompt) {
        try {
            if (API_KEY == null || API_KEY.isEmpty()) {
                System.err.println("OpenAI API key not configured!");
                return ResponseEntity.status(500).body("API key not configured");
            }

            // Configure RestTemplate with proper request factory
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(10000);
            factory.setReadTimeout(30000);
            RestTemplate restTemplate = new RestTemplate(
                    new BufferingClientHttpRequestFactory(factory));

            // Create request body
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> request = Map.of(
                    "model", "gpt-5.4-mini",
                    "messages", List.of(
                            Map.of("role", "user", "content", prompt)));
            String jsonRequest = mapper.writeValueAsString(request);

            System.out.println("Sending request to OpenAI API...");

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(API_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    "https://api.openai.com/v1/chat/completions",
                    HttpMethod.POST,
                    entity,
                    String.class);

            System.out.println("OpenAI API response received with status: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            System.err.println("Error calling AI API: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error calling AI API: " + e.getMessage());
        }
    }

    private JsonNode extractContentFromResponse(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            String content = root.path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

            return mapper.readTree(content);
        } catch (Exception e) {
            System.err.println("Error extracting content from AI response: " + e.getMessage());
            e.printStackTrace();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.createObjectNode(); // Return empty JSON object on error
        }
    }

    private LeadAnalysis parseLeadAnalysis(String response) {
        try {
            // Extract the content from the API response
            JsonNode analysisJson = extractContentFromResponse(response);

            LeadAnalysis analysis = new LeadAnalysis();
            analysis.setIntent(analysisJson.path("intent").asText());
            analysis.setBudget(analysisJson.path("budget").asText());
            analysis.setUrgency(analysisJson.path("urgency").asText());
            analysis.setLeadScore(analysisJson.path("leadScore").asText());

            return analysis;
        } catch (Exception e) {
            System.err.println("Error parsing AI response: " + e.getMessage());
            return new LeadAnalysis();
        }
    }

    public JsonNode generateContextualEmail(Lead lead) {
        String prompt = promptLoaderService.loadEmailPrompt(lead);

        System.out.println("\nGenerating contextual email content for lead: " + lead.getName());
        ResponseEntity<String> response = callOpenAiApi(prompt);

        JsonNode analysisJson = extractContentFromResponse(response.getBody());

        return analysisJson;
    }

}