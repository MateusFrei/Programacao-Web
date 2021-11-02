
package br.com.sistema_estoque.exception;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ValidationError extends Error{
    
    private List<PropertyError> erros = new ArrayList<>();

    public ValidationError(Calendar timestamp, Integer status, String error, String massage, String path) {
        super(timestamp, status, error, massage, path);
    }

    public List<PropertyError> getErros() {
        return erros;
    }

    public void setErros(List<PropertyError> erros) {
        this.erros = erros;
    }
    
}
