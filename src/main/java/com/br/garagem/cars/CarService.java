package com.br.garagem.cars;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // buscar carro por id
    public Optional<Car> getCarByID(Integer id) {
        return repo.findById(id);
    }

    public String saveNewCar(String placa, String modelo) {
        if (placa != null) {
            return "Erro: O campo placa é obrigatório!";
        }
        if ((placa.length() != 8) || (!placa.contains("-"))) {
            return "Erro: A placa deve conter o padrão XXX-9999";
        }

        Car car = new Car();
        car.setPlaca(placa);
        car.setModelo(modelo);

        repo.save(car);
        return "Sucesso: Cadastro concluído!";
    }

    public String updateCar(Integer id, String placa, String modelo) {

        if (placa != null) {
            return "Erro: O campo placa é obrigatório!";
        }
        if ((placa.length() != 8) || (!placa.contains("-"))) {
            return "Erro: A placa deve conter o padrão XXX-9999";
        }

        Car car = new Car();
        Boolean carExist = repo.existsById(id);

        if (!carExist) {
            return "Erro: Carro não encontrado!";
        }
        Optional<Car> oldCar = repo.findById(id);
        car.setId(id);
        car.setData_entrada(oldCar.get().getData_entrada());
        car.setPlaca(placa);
        car.setModelo(modelo);
        car.setData_saida(oldCar.get().getData_saida());
        repo.save(car);
        return "Sucesso: Alteração concluída!";
    }

}
