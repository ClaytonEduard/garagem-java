package com.br.garagem.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.garagem.user.User;
import com.br.garagem.user.UserService;
import com.br.garagem.valor.Valor;
import com.br.garagem.valor.ValorService;

@Service
public class Seeder {
    @Autowired
    private UserService userService;
    @Autowired
    private ValorService valorService;

    public void populateDataBase() {
        User user = new User();
        user.setId(1);
        user.setNome("João");
        user.setUsuario("admin");
        user.setSenha("123456");
        this.userService.createOrUpdate(user);

        Valor valor = new Valor();
        valor.setId(1);
        valor.setPrimeira_hora(6.00);
        valor.setDemais_horas(4.00);
        this.valorService.createOrUpdate(valor);
    }
}
