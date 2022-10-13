package com.javatask.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private String nick;
    private List<VehicleModel> cars = new ArrayList<>();

    public UserModel() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public List<VehicleModel> getCars() {
        return cars;
    }

    public void setCar(VehicleModel cars) {
        this.cars.add(cars);
    }
}
