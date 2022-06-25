package com.br.garagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.garagem.utils.Seeder;

@SpringBootApplication
public class GaragemApplication implements CommandLineRunner {
	@Autowired
	private Seeder seeder;

	public static void main(String[] args) {
		SpringApplication.run(GaragemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.seeder.populateDataBase();
	}

	
}
