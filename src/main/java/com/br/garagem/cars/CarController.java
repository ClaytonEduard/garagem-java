package com.br.garagem.cars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

   @Autowired
   private CarService service;

   // lista de carros que estao estacionados
   @GetMapping("/parking-in")
   public String showParketStopPage(Model model) {
      List<Car> listCars = service.listParkedCars();
      model.addAttribute("listCars", listCars);
      return "parking-in";
   }

   @GetMapping("/parking-out")
   public String showParketOutPage(Model model) {
      List<Car> listCars = service.listCarsOut();
      model.addAttribute("listCars", listCars);
      return "parking-out";
   }

}
