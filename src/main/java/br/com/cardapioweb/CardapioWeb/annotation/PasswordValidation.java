/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author lucas
 */

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})//especifica os elementos que farão uso da annotation
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation {
    String message() default "Senha não atende aos requisitos";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
    
    
}
