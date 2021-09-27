
package br.com.sistema_estoque.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class Emailvalidador implements ConstraintValidator<Emailvalidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return false;
        if (value.contains(" ")) return false;
        return value.matches("[\\w\\s]+[@]+[\\w\\s]+[.]+[\\w\\s]+");
        
    }
    
}
