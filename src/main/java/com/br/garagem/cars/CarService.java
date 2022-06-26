package com.br.garagem.cars;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.garagem.valor.Valor;
import com.br.garagem.valor.ValorService;

@Service
public class CarService {
    @Autowired
    private CarRepository repo;
    @Autowired
    private ValorService valorService;

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
        if (placa == null) {
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

        if (placa == null) {
            return "Erro: O campo placa é obrigatório!";
        }
        if ((placa.length() != 8) || (!placa.contains("-"))) {
            return "Erro: A placa deve conter o padrão XXX-9999";
        }

        Boolean carExist = repo.existsById(id);

        if (!carExist) {
            return "Erro: Carro não encontrado!";
        }
        Optional<Car> car = repo.findById(id);
        car.get().setPlaca(placa);
        car.get().setModelo(modelo);
        repo.save(car.get());
        return "Sucesso: Alteração concluída!";
    }

    // CONFIRMAR SAIDA DO CARRO
    public void confirmOutCar(Integer id) {
        Optional<Car> car = getCarByID(id);
        if (car.isPresent()) {
            Valor valor = new Valor();
            valor = this.valorService.getValor().get();
            car.get().setData_saida(new Date());
            Double totalCalculado = car.get().calculateTotalPay(valor.getPrimeira_hora(), valor.getDemais_horas());
            System.out.println(totalCalculado);
            repo.save(car.get());
        }

    }

    // TOTALIZADORA CARRO
    public Optional<Car> getCarResume(Integer id) {
        Optional<Car> car = getCarByID(id);
        Valor valor = new Valor();
        valor = valorService.getValor().get();
        car.get().setData_saida(new Date());
        car.get().calculateTotalPay(valor.getPrimeira_hora(), valor.getDemais_horas());
        return car;
    }

}
