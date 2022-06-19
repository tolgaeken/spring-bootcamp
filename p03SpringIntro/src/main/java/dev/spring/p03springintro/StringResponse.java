package dev.spring.p03springintro;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringResponse {
    private String response;

    public StringResponse(String response) {
        this.response = response;
    }

    public StringResponse() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


}
