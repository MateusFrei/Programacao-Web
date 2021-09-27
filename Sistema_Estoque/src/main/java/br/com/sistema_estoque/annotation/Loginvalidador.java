
package br.com.sistema_estoque.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Loginvalidador implements ConstraintValidator<LoginValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return false;
        if(value.contains(" ")) return false;
        return value.length() <= 50;
    }
    
}
