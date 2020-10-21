/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perceiving.controller;

import com.jpa.model.Application;
import com.jpa.model.Function;
import com.jpa.model.Module;
import com.jpa.model.Rol;
import com.jpa.model.User;
import com.jpa.service.Assignments;
import com.jpa.service.Service_Application;
import com.jpa.service.Service_Function;
import com.jpa.service.Service_Module;
import com.jpa.service.Service_Rol;
import com.jpa.service.Service_User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Anny Mora 
 */
@ManagedBean(name = "userController")
@ViewScoped
public class UserController implements Serializable {

    private List<User> filteredUser;

    private List<User> listUser = new ArrayList<>();
    private List<Application> listApp = new ArrayList<>();
    private List<Rol> listRol = new ArrayList<>();
    private List<Module> listModule = new ArrayList<>();
    private List<Function> listFuncionalities = new ArrayList<>();

//    INSTANCIAS DE MODELO
    private User user = new User();
    private Application app = new Application();
    private Rol rol = new Rol();
    private Module module = new Module();
    private Function function = new Function();
//    INSTANCIAS DE MODELO

//   INSTANCIAS DE SERVICIO
    private final Service_User serviceUser = new Service_User();
    private final Service_Application serviceApp = new Service_Application();
    private final Service_Rol serviceRol = new Service_Rol();
    private final Service_Module serviceModule = new Service_Module();
    private final Service_Function serviceFunction = new Service_Function();

    private Assignments assign = new Assignments();
//     INSTANCIAS DE SERVICIO

    private User verify = null;

//    METODO PARA ASIGNAR APP
    public void assign() {

        User verify = serviceUser.verifyId(this.user.getIdUser());
        if (verify != null) {
            assign.addAppUser(this.user.getIdUser(), this.app.getId()); // ASIGNA LA APLICACION AL USUARIO 
            assign.addUserRol(this.user.getIdUser(), this.rol.getIdRol()); // ASIGNA EL ROL AL USUARIO
            assign.addModuleRol(this.rol.getIdRol(), this.module.getIdModule()); // ASIGNA EL MODULO AL ROL
            assign.addModuleApp(this.module.getIdModule(), this.app.getId()); // ASIGNA A LA APLICACION EL MODULO
            assign.addModuleFunction(this.module.getIdModule(), this.function.getIdFunction()); // ASIGNA AL MODULO LAS FUNCIONES
            System.out.println(
                    "Se asignó al usuario: " + user.getUser_name()
                    + " la aplicación: " + app.getApplication_name()
                    + " con el rol: " + rol.getRol_name()
                    + " y el modulo: " + module.getModule_name()
                    + " y la funcion: " + function.getFunction_name());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "ÉXITO", "¡La asignación se realizó correctamente!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "ERROR", "¡No fue posible la asignación!"));
        }
    }

//    METODO PARA CREAR UN NUEVO USUARIO
    public String createUser() {
        verify = serviceUser.verifyExists(this.user.getEmail());
        if (verify == null) {
            serviceUser.createUser(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "ÉXITO", "¡Usuario creado con éxito!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "ERROR", "El usuario ya existe"));
        }
        return "";
    }

    public List<User> getListUser() {
        listUser = serviceUser.getAll();
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public List<Application> getListApp() {
        listApp = serviceApp.getAll();
        return listApp;
    }

    public void setListApp(List<Application> listApp) {
        this.listApp = listApp;
    }

    public List<Rol> getListRol() {
        listRol = serviceRol.getAll();
        return listRol;
    }

    public void setListRol(List<Rol> listRol) {
        this.listRol = listRol;
    }

    public List<Module> getListModule() {
        listModule = serviceModule.getAll();
        return listModule;
    }

    public void setListModule(List<Module> listModule) {
        this.listModule = listModule;
    }

    public List<Function> getListFuncionalities() {
        listFuncionalities = serviceFunction.getAll();
        return listFuncionalities;
    }

    public void setListFuncionalities(List<Function> listFuncionalities) {
        this.listFuncionalities = listFuncionalities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Assignments getAssign() {
        return assign;
    }

    public void setAssign(Assignments assign) {
        this.assign = assign;
    }

    public List<User> getFilteredUser() {
        return filteredUser;
    }

    public void setFilteredUser(List<User> filteredUser) {
        this.filteredUser = filteredUser;
    }

}
