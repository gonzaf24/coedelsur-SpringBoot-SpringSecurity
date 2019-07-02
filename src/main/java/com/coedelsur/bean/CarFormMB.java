/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coedelsur.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import com.coedelsur.model.Car;
import com.coedelsur.service.CarService;
import com.coedelsur.util.Utils;
import com.github.adminfaces.template.exception.AccessDeniedException;

import static com.coedelsur.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

@Named
@ViewScoped
public class CarFormMB implements Serializable {

    @Inject
    CarService carService;

    private Integer id;
    private Car car;
    
    @PostConstruct
    public void prueba () {
    	int i = 0 ;
    	i=9;
    }

    public void init() {
//        if (Faces.isAjaxRequest()) {
//            return;
//        }
//        if (has(id)) {
    	int h = 0;
            car = carService.findById(1);
//        } else {
//            car = new Car();
//        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public void remove() throws IOException {
        if (!Utils.isUserInRole("ADMIN")) {
            throw new AccessDeniedException("User not authorized! Only role <b>admin</b> can remove cars.");
        }
        if (has(car) && has(car.getId())) {
            carService.remove(car);
            addDetailMessage("Car " + car.getModel()
                    + " removed successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect("user/car-list.jsf");
        }
    }

    public void save() {
        String msg;
        if (car.getId() == null) {
            carService.insert(car);
            msg = "Car " + car.getModel() + " created successfully";
        } else {
            carService.update(car);
            msg = "Car " + car.getModel() + " updated successfully";
        }
        addDetailMessage(msg);
    }

    public void clear() {
        car = new Car();
        id = null;
    }

    public boolean isNew() {
        return car == null || car.getId() == null;
    }


}
