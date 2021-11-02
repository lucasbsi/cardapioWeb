/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.cardapioweb.CardapioWeb.exception.Error;
import java.util.Calendar;

/**
 *
 * @author lucas
 */
@RestControllerAdvice //catalisador de exceção global
public class MyRestControllerAdvice {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity erroPadrao(Exception e, HttpServletRequest request){
        Error erro = new Error(
                Calendar.getInstance(), 
                HttpStatus.BAD_REQUEST.value(), 
                HttpStatus.BAD_REQUEST.name(), 
                e.getMessage(), 
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    
}
