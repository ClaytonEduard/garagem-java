package com.br.garagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.garagem.user.User;
import com.br.garagem.user.UserService;
import com.br.garagem.valor.Valor;
import com.br.garagem.valor.ValorService;

@SpringBootApplication
public class GaragemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GaragemApplication.class, args);
		System.out.println("Ola");
	}

	@Autowired
	private UserService userService;
	@Autowired
	private ValorService valorService;

	@Override
	public void run(String... args) throws Exception {
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
