package com.br.garagem.cars;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Integer> {
  
}
