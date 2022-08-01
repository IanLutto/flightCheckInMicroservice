package com.lutomiah.flightcheckin.flightcheckin.model;
/**
 *
 * @author lutomiah.ian
 */
public class ApiResponse {
    private String message;
    private String narration;
    private String responseCode;
    private Object responseBody;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "ApiResponse{" + "message=" + message + ", narration=" + narration + ", responseCode =" + responseCode + ", responseBody = " + responseBody + '}';
    }
}
