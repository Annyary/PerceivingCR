/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.perceiving.controller;

import com.jpa.model.Module;
import com.jpa.model.Rol;
import com.jpa.service.Service_Rol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jeffrey Valerio BEAN PARA ADMINISTRAR LA INTERACCION CON ROLES
 */
@ManagedBean(name = "rolController")
@ViewScoped
public class RolController implements Serializable {

    private List<Rol> listRoles = new ArrayList<>();
    private List<Rol> filteredRol;
    private Rol selectedRol;

//    INSTANCIAS DEL MODELO
    private Rol rol = new Rol();
    private Module module = new Module();
//    INSTANCIAS DEL MODELO

//    INSTANCIAS DEL SERVICIO
    private final Service_Rol serviceRol = new Service_Rol();
//    INSTANCIAS DEL SERVICIO

    private Rol verify = null;

    public RolController() {
    }

    public String createRol() {
        verify = serviceRol.verifyExists(this.rol.getRol_name());
        if (verify == null) {
            serviceRol.createRol(rol);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "ÉXITO", "¡Rol creado con éxito!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "ERROR", "El rol ya existe"));
        }
        return "";
    }
    
    public void ModifyRolModule(){
        
    }
    

    public List<Rol> getListRoles() {
        listRoles = serviceRol.getAll();
        return listRoles;
    }

    public void setListRoles(List<Rol> listRoles) {
        this.listRoles = listRoles;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getFilteredRol() {
        return filteredRol;
    }

    public void setFilteredRol(List<Rol> filteredRol) {
        this.filteredRol = filteredRol;
    }

    public Rol getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

}
