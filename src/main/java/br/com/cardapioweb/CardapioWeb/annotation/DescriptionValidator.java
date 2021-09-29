/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.annotation;

import java.lang.annotation.Annotation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author lucas
 */
public class DescriptionValidator implements ConstraintValidator<DescriptionValidation, String>{

    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    	return Character.isUpperCase(t.codePointAt(0));
    }
    
    
}
