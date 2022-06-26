package com.br.garagem.cars;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

   @Autowired
   private CarService service;

   // lista de carros que estao estacionados
   @GetMapping("/parking-in")
   public String showParkedStopPage(Model model) {
      List<Car> listCars = service.listParkedCars();
      model.addAttribute("listCars", listCars);
      return "parking-in";
   }

   @PostMapping("/new-car")
   public String saveNewCar(@ModelAttribute Car car, Model model) {
      model.addAttribute("car", car);
      String message = service.saveNewCar(car.getPlaca(), car.getModelo());
      if (message.contains("Erro")) {
         model.addAttribute("Erro", message);
         return "new-car";
      };
      return "redirect:/parking-in";
   }

   @GetMapping("/new-car")
   public String showNewCarForm() {
      return "new-car";
   }

   @GetMapping("/parking-out")
   public String showParkedOutPage(Model model) {
      List<Car> listCars = service.listCarsOut();
      model.addAttribute("listCars", listCars);
      return "parking-out";
   }

   @GetMapping("/edit-car/{id}")
   public String showEditForm(Model model, @PathVariable("id") Integer id) {
      Optional<Car> car = this.service.getCarByID(id);
      model.addAttribute("car", car);
      return "edit-car";
   }

   @GetMapping("/out-car/{id}")
   public String showOutForm(Model model, @PathVariable("id") Integer id) {
      Optional<Car> car = this.service.getCarResume(id);
      model.addAttribute("car", car);
      return "out-car";
   }

}
