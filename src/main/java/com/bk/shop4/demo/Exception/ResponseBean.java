package com.bk.shop4.demo.Exception;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseBean implements Serializable {

	@Autowired
	private static final long serialVersionUID = 1L;
	 @JsonProperty(value="CODE")
     int code;
     @JsonProperty(value="DESCRIPTION")
     String description;
     @JsonProperty(value="OBJECT")
     Object object;

    public ResponseBean(int code, String description, Object object) {
        this.code = code;
        this.description = description;
        this.object = object;
    }
    public ResponseBean(){}
     public int getCode() {
         return code;
     }
     public void setCode(int code) {
         this.code = code;
     }
     public String getDescription() {
         return description;
     }
     public void setDescription(String description) {
         this.description = description;
     }
     public Object getObject() {
         return object;
     }
     public void setObject(Object object) {
         this.object = object;
     }
}
