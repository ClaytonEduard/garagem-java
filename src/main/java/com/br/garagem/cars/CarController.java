package com.br.garagem.cars;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

   @GetMapping("/edit-car/{id}")
   public String showEditform(Model model,@PathVariable("id") Integer id) {
      Optional<Car> car = this.service.getCarByID(id);
      model.addAttribute("car", car);
      return "edit-car";
   }

   @GetMapping("/out-car/{id}")
   public String showOutform(Model model, @PathVariable("id") Integer id) {
      Optional<Car> car = this.service.getCarResume(id);
      model.addAttribute("car", car);
      return "out-car";
   }

}
