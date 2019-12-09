package com.codegym.controller;


import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.CityService;

import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CityController {
    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> countries(){
        return countryService.findAll();
    }

    @GetMapping("/cities")
    public ModelAndView listCities(@PageableDefault(size=5)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/city/list");
        Iterable<City> cities = cityService.findAll(pageable);
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
//    @PostMapping("/create-city")
//    public ModelAndView saveCity(@Valid @ModelAttribute("city") City city, BindingResult bindingResult){
//        ModelAndView modelAndView = new ModelAndView("/city/create");
//        if(bindingResult.hasErrors()){
//            modelAndView.addObject("message","Fail create city");
//            return modelAndView;
//        }else {
//            cityService.save(city);
//            modelAndView.addObject("city",city);
//            modelAndView.addObject("message","New City Create successfully!");
//            //modelAndView.addObject("/city/list");
//            return modelAndView;
//        }
//    }

    @PostMapping("/create-city")
    public String saveCity(@Valid @ModelAttribute("city") City city,RedirectAttributes redirectAttributes, BindingResult bindingResult){
        //ModelAndView modelAndView = new ModelAndView("/city/create");
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("message","Fail create city");
            return "/create-city";
        }else {
            cityService.save(city);
            redirectAttributes.addFlashAttribute("message","New City Create successfully!");
            return "redirect:cities";
        }
    }

    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        City city = cityService.findById(id);
        if(city != null) {
            ModelAndView modelAndView = new ModelAndView("/city/edit");
            modelAndView.addObject("city", city);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

//    @PostMapping("/edit-city")
//    public ModelAndView updateCity(@Valid @ModelAttribute("city") City city,BindingResult bindingResult){
//        ModelAndView modelAndView = new ModelAndView("/city/edit");
//        if(bindingResult.hasErrors()){
//            modelAndView.addObject("message","Fail Edit City");
//            return modelAndView;
//        }else{
//            cityService.save(city);
//            modelAndView.addObject("city", city);
//            modelAndView.addObject("message", "City Updated successfully");
//            return modelAndView;
//        }
//
//    }
    @PostMapping("/edit-city")
    public String updateCity(@Valid @ModelAttribute("city") City city,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("message","Fail Edit City");
            return "/edit-city";
        }else{
            cityService.save(city);
            redirectAttributes.addFlashAttribute("city",city);
            redirectAttributes.addFlashAttribute("message", "City Updated successfully");
            return "redirect:cities";
        }

    }
    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        City city = cityService.findById(id);
        if(city != null) {
            ModelAndView modelAndView = new ModelAndView("/city/delete");
            modelAndView.addObject("city", city);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-city")
    public String deleteCity(@ModelAttribute("city") City city){
        cityService.remove(city.getId());
        return "redirect:cities";
    }

//    @GetMapping("/view-city/{id}")
//    public ModelAndView viewcity(@PathVariable("id")Long id){
//        City city = cityService.findById(id);
//        if(city == null){
//            return new ModelAndView("/error.404");
//        }
//        Iterable<Book> materials = bookService.findAllBycity(city);
//        ModelAndView modelAndView = new ModelAndView("/city/view");
//        modelAndView.addObject("city",city);
//        modelAndView.addObject("materials",materials);
//        return modelAndView;
//    }
}
