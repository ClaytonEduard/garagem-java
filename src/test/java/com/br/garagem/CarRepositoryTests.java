package com.br.garagem;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.br.garagem.cars.Car;
import com.br.garagem.cars.CarRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CarRepositoryTests {
    @Autowired
    private CarRepository repo; 

    @Test
    public void testAddCar() {
        Date dataAtual;

        dataAtual = new Date();

        Car car = new Car();

        Integer eIgual = car.getData_entrada().compareTo(dataAtual);
        System.out.println("E igual  " + eIgual);

        car.setPlaca("HYV-1522");
        car.setModelo("Chevrolet");
        Car savedCar = repo.save(car);

        Assertions.assertThat(eIgual).isEqualTo(0);
        Assertions.assertThat(savedCar).isNotNull();
        Assertions.assertThat(savedCar.getId()).isGreaterThan(0);
        Assertions.assertThat(savedCar.getTempo()).isEqualTo(60);

    }

}
