package com.br.garagem.valor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValorService {
    @Autowired
    private ValorRepository repo;

    public Optional<Valor> getValor() {
        return this.repo.findById(1);
    }

    public void createOrUpdate(Valor valor) {
        this.repo.save(valor);
    }

}
