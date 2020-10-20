/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perceiving.controller;

import com.jpa.model.Function;
import com.jpa.service.Service_Function;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jeffrey Valerio
 */
@ManagedBean
@ViewScoped
public class FunctionController implements Serializable{

    private List<Function> listFunction = new ArrayList<>();

//    INSTANCIAS DEL MODELO
    private Function function = new Function();
//    INSTANCIAS DEL MODELO

//    INSTANCIAS DEL SERVICIO
    private final Service_Function serviceFunction = new Service_Function();
//    INSTANCIAS DEL SERVICIO

    private Function verify = null;

    public FunctionController() {
    }

//    public String createFunction() {
//        verify = serviceFunction.verifyExists(this.function.getFunction_name());
//        if (verify == null) {
//            serviceFunction.createFunction(function);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
//                    "ÉXITO", "¡Function creado con éxito!"));
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                    "ERROR", "El rol ya existe"));
//        }
//        return "";
//    }

    public List<Function> getListFunction() {
        listFunction = serviceFunction.getAll();
        return listFunction;
    }

    public void setListFunction(List<Function> listFunction) {
        this.listFunction = listFunction;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

}
