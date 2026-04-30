
INSERT INTO leads (name, email, message, intent, budget, urgency, lead_score, created_at) VALUES
-- HOT leads
('John Carter', 'john@example.com', 'I need help automating lead follow-ups for my agency. Budget is flexible, just need this done fast.', 'website development', '$3000', 'high', 'HOT', CURRENT_TIMESTAMP),
('Sarah Lee', 'sarah@example.com', 'Looking to automate onboarding for my agency. Can we talk today?', 'automation system', NULL, 'high', 'HOT', CURRENT_TIMESTAMP),
('Mike Johnson', 'mike@example.com', 'Need Stripe integration for my app within 2 weeks', 'payment integration', NULL, 'medium', 'HOT', CURRENT_TIMESTAMP),

-- WARM leads
('Emily Davis', 'emily@example.com', 'Thinking about building a website, what would it cost?', 'website inquiry', NULL, 'low', 'WARM', CURRENT_TIMESTAMP),
('Chris Brown', 'chris@example.com', 'We might need help with automation tools in the future', 'automation inquiry', NULL, 'low', 'WARM', CURRENT_TIMESTAMP),
('Anna White', 'anna@example.com', 'Do you have experience with e-commerce sites?', 'ecommerce question', NULL, 'low', 'WARM', CURRENT_TIMESTAMP),

-- COLD leads
('Random User', 'random@example.com', 'How much do you charge?', 'pricing inquiry', NULL, 'low', 'COLD', CURRENT_TIMESTAMP),
('Test Person', 'test@example.com', 'Just browsing, not ready yet', 'general inquiry', NULL, 'low', 'COLD', CURRENT_TIMESTAMP),
('Startup Guy', 'startup@example.com', 'I have an app idea but no budget yet', 'startup idea', NULL, 'low', 'COLD', CURRENT_TIMESTAMP),

-- Edge cases
('Low Budget Fast', 'fast@example.com', 'Need landing page, $500 budget, ASAP', 'landing page', '$500', 'high', 'HOT', CURRENT_TIMESTAMP),
('Future Planner', 'future@example.com', 'Planning redesign next quarter, gathering quotes', 'website redesign', NULL, 'low', 'WARM', CURRENT_TIMESTAMP),
('Vague Lead', 'vague@example.com', 'Can you send pricing info?', 'pricing request', NULL, 'low', 'COLD', CURRENT_TIMESTAMP);