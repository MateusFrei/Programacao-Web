
package br.com.sistema_estoque.exception;

import java.io.Serializable;
import java.util.Calendar;


public class Error implements Serializable{
    private static final long SerialVersionUID = 1L;
    
    private Calendar timestamp;
    private Integer status;
    private String error, massage, path;

    public Error(Calendar timestamp, Integer status, String error, String massage, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.massage = massage;
        this.path = path;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
