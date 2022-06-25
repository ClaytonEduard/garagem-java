package com.br.garagem.cars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository repo;

    // listar carros
    public List<Car> listParkedCars() {
        return (List<Car>) repo.findAll();
    }
    


}
