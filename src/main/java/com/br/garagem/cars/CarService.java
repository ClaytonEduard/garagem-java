package com.br.garagem.cars;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository repo;

    // listar carros
    public List<Car> listParkedCars() {
        ArrayList<Car> carList = new ArrayList<>();
        repo.findAll().forEach(car -> {
            if (car.getData_saida() == null) {
                carList.add(car);
            }
            ;
        });
        return carList;
    }

    public List<Car> listCarsOut() {
        ArrayList<Car> carList = new ArrayList<>();
        repo.findAll().forEach(car -> {
            if (car.getData_saida() != null) {
                carList.add(car);
            }
            ;
        });
        return carList;
    }

}
