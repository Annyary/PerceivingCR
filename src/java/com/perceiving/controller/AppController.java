/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perceiving.controller;

import com.jpa.model.Application;
import com.jpa.service.Service_Application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Anny Mora
 */
@ManagedBean(name = "appController")
@SessionScoped
public class AppController implements Serializable {

    private List<Application> filteredApp;
    private List<Application> listApp = new ArrayList<>();

//    INSTANCIA DEL MODELO
    private Application app = new Application();
//    INSTANCIA DEL MODELO

//    INSTANCIA DEL SERVICIO
    private final Service_Application serviceApp = new Service_Application();
//    INSTANCIA DEL SERVICIO

    private Application verify = null;

    public AppController() {
    }

    @PostConstruct
    public void init() {
        listApp = new ArrayList<>();
        for (Application application : listApp) {
            System.out.println(application.getId());
            listApp.add(application);
        }
    }

    public String createApp() {
        verify = serviceApp.verifyExists(this.app.getApplication_name());
        if (verify == null) {
            serviceApp.createApplication(app);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "ÉXITO", "¡Aplicación creada con éxito!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "ERROR", "La aplicación ya existe"));
        }
        return "";
    }

    public List<Application> getListApp() {
        listApp = serviceApp.getAll();
        return listApp;
    }

    public void setListApp(List<Application> listApp) {
        this.listApp = listApp;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public List<Application> getFilteredApp() {
        return filteredApp;
    }

    public void setFilteredApp(List<Application> filteredApp) {
        this.filteredApp = filteredApp;
    }

    public Service_Application getServiceApp() {
        return serviceApp;
    }

}
