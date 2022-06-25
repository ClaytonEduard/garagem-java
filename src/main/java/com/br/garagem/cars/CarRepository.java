package com.br.garagem.cars;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {

    /* @todo implementar o metodo para listar somente os corro no estaciomanento */
}
