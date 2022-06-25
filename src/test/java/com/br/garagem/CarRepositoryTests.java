package com.br.garagem;


import java.time.LocalDateTime;
import java.time.ZoneId;
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

        car.setPlaca("SEF-5268");
        car.setModelo("FORD");
        Car savedCar = repo.save(car);

        Assertions.assertThat(eIgual).isEqualTo(0);
        Assertions.assertThat(savedCar).isNotNull();
        Assertions.assertThat(savedCar.getId()).isGreaterThan(0);
        Assertions.assertThat(savedCar.getTempo()).isEqualTo(60);

    }

    @Test
    public void calcularUmaHora(){
        Car car = new Car();    
        Date d2 = new Date();
        Date d1 = new Date();

        LocalDateTime localDateTime = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusHours(1);
        d2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        car.setData_entrada(d1);
        car.setData_saida(d2);

        Double result = car.calculateTotalPay(6.0);
        Assertions.assertThat(result).isEqualTo(6.0);
    }

    @Test
    public void calcularDuasHoras(){
        Car car = new Car();    
        Date d2 = new Date();
        Date d1 = new Date();

        LocalDateTime localDateTime = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusHours(2);
        d2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        car.setData_entrada(d1);
        car.setData_saida(d2);

        Double result = car.calculateTotalPay(6.0);
        Assertions.assertThat(result).isEqualTo(12.0);
    }

}
