/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perceiving.controller;

import com.jpa.model.Module;
import com.jpa.service.Service_Module;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Anny Mora
 */
@ManagedBean
@ViewScoped
public class ModuleController  implements Serializable {

    private List<Module> listModule = new ArrayList<>();
    private List<Module> filteredModule;
    private Module selectedModule;

//    INSTANCIAS DEL MODELO
    private Module module = new Module();
//    INSTANCIAS DEL MODELO

//    INSTANCIAS DEL SERVICIO
    private final Service_Module serviceModule = new Service_Module();
//    INSTANCIAS DEL SERVICIO

    private Module verify = null;

    public ModuleController() {
    }

    public String createModule() {
        verify = serviceModule.verifyExists(this.module.getModule_name());
        if (verify == null) {
            serviceModule.createModule(module);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "ÉXITO", "¡Module creado con éxito!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "ERROR", "El rol ya existe"));
        }
        return "";
    }

    public List<Module> getListModule() {
        listModule = serviceModule.getAll();
        return listModule;
    }

    public void setListModule(List<Module> listModule) {
        this.listModule = listModule;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Module> getFilteredModule() {
        return filteredModule;
    }

    public void setFilteredModule(List<Module> filteredModule) {
        this.filteredModule = filteredModule;
    }

    public Module getSelectedModule() {
        return selectedModule;
    }

    public void setSelectedModule(Module selectedModule) {
        this.selectedModule = selectedModule;
    }

}
