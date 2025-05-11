package main.com.studentfinance.model;

import java.util.Date;

public class ConsultationRequest {
    private String id;
    private String question;
    private Date timestamp;
    private String response;
    private ConsultationStatus status;

    public void createRequest() {
        // Implementation
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void updateStatus(ConsultationStatus status) {
        this.status = status;
    }

    // getters, setters, constructors
}