package com.codegym.controller;


import com.codegym.model.Country;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/countries")
    public ModelAndView listCountries(){
        Iterable<Country> countries = countryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/country/list");
        modelAndView.addObject("countries",countries);
        return modelAndView;
    }

    @GetMapping("/create-country")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/country/create");
        modelAndView.addObject("country", new Country());
        return modelAndView;
    }
    @PostMapping("/create-country")
    public String savecountry(@ModelAttribute("country") Country country, RedirectAttributes redirectAttributes){
        countryService.save(country);
//        ModelAndView modelAndView = new ModelAndView("/country/create");
//        modelAndView.addObject("country", country);
        redirectAttributes.addFlashAttribute("message","New country create successfully!");
        return "redirect:countries";
    }
    @GetMapping("/edit-country/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Country country = countryService.findById(id);
        if(country != null) {
            ModelAndView modelAndView = new ModelAndView("/country/edit");
            modelAndView.addObject("country", country);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-country")
    public String updateCountry(@ModelAttribute("country") Country country,RedirectAttributes redirectAttributes){
        countryService.save(country);
//        ModelAndView modelAndView = new ModelAndView("/country/list");
//        modelAndView.addObject("country", country);
//        modelAndView.addObject("message", "country updated successfully");
        redirectAttributes.addFlashAttribute("message", "country updated successfully");
        return "redirect:countries";
    }
    @GetMapping("/delete-country/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Country country = countryService.findById(id);
        if(country != null) {
            ModelAndView modelAndView = new ModelAndView("/country/delete");
            modelAndView.addObject("country", country);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-country")
    public String deleteCountry(@ModelAttribute("country") Country country){
        countryService.remove(country.getId());
        return "redirect:countries";
    }

//    @GetMapping("/view-country/{id}")
//    public ModelAndView viewcountry(@PathVariable("id")Long id){
//        Country country = countryService.findById(id);
//        if(country == null){
//            return new ModelAndView("/error.404");
//        }
//        Iterable<Book> materials = bookService.findAllBycountry(country);
//        ModelAndView modelAndView = new ModelAndView("/country/view");
//        modelAndView.addObject("country",country);
//        modelAndView.addObject("materials",materials);
//        return modelAndView;
//    }
}
