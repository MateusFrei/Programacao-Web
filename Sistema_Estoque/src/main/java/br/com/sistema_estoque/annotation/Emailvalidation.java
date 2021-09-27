
package br.com.sistema_estoque.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = Emailvalidador.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Emailvalidation {
    String message() default "Email invalido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
