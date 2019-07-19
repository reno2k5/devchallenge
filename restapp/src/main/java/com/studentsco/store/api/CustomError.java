/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.studentsco.store.api;

public class CustomError extends Exception{
    public CustomError(String message){
        super(message);
    }
}
